package controller;

import db.Converter;
import db.Manager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

public class GameController {
    public String getSlot(String username, String slot)
    {
        String slotVal = "0";
        ResultSet rs;
        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT "+slot+" FROM gamestates WHERE user_id='"+username+"'");
            if(rs.next())
            {
                slotVal = rs.getString(slot);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Manager.disconnect();

        return slotVal;
    }

    public int getMoves(String username)
    {
        int val = 0;
        ResultSet rs;
        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT moves FROM gamestates WHERE user_id='"+username+"'");
            if(rs.next())
            {
                val = rs.getInt("moves");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Manager.disconnect();

        return val;
    }

    public int decrementMoves(String username)
    {
        int mod = 0;
        ResultSet rs;
        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Statement stmt = con.createStatement();
            mod = stmt.executeUpdate("UPDATE gamestates SET moves = (moves+1) WHERE user_id='"+username+"'");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Manager.disconnect();

        return mod;
    }

    public int deleteGames(String username)
    {
        int moves = 0;
        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Statement stmt = con.createStatement();
            moves = stmt.executeUpdate("DELETE FROM gamestates WHERE user_id='"+username+"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Manager.disconnect();

        return moves;
    }

    public int newGame(String username)
    {
        int mod = 0;
        String result = "";
        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Manager.connect();
            con = Manager.getConnection();
            Statement stmt = con.createStatement();

//            stmt.executeUpdate("INSERT INTO gamestates (user_id, cell_1, cell_2, cell_3, cell_4, cell_5, cell_6, cell_7, cell_8, cell_9, moves) values('"+username+"', 0,0,0,0,0,0,0,0,0,9)");
//
//

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO gamestates (user_id, cell_1, cell_2, cell_3, cell_4, cell_5, cell_6, cell_7, cell_8, cell_9, moves) values(?, 0,0,0,0,0,0,0,0,0,0)");
            pstmt.setString(1, username);
            mod = pstmt.executeUpdate();

            Manager.disconnect();
        } catch (SQLException e)  {
            e.printStackTrace();
            System.out.println(e);
        }

        return mod;
    }

    public int exchangeSlots(String username, String slot1, String slot2)
    {
        String slotVal1 = getSlot(username, slot1);
        String slotVal2 = getSlot(username, slot2);

        System.out.println("Exchange: "+slot1+":" +slotVal1 + " <--> "+slot2+":"+slotVal2);

        int mod = 0;

        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Statement stmt = con.createStatement();
            mod = stmt.executeUpdate("UPDATE gamestates SET "+slot1+"="+slotVal2+", " + slot2 + "="+slotVal1+" WHERE user_id='"+username+"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Manager.disconnect();

        return mod;
    }

    public int emptySlots(String username, String slot)
    {
        int mod = 0;

        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Statement stmt = con.createStatement();
            mod = stmt.executeUpdate("UPDATE gamestates SET "+slot+"=0 WHERE user_id='"+username+"'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Manager.disconnect();

        return mod;
    }

    public String movePiece(String username, String parent, String piece, String target)
    {
        System.out.println("PUT: "+piece+" ON: "+target+" FROM: "+parent+"WHILE_TARGET("+getSlot(username, target)+")");
        if(getSlot(username, target).equals("0"))
        {
            if(parent.equals("free_pieces"))
            {
                emptySlots(username, target);
            }

            ResultSet rs;
            Manager.connect();
            Connection con = Manager.getConnection();
            try {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("UPDATE gamestates SET "+target+"="+piece+" WHERE user_id='"+username+"'");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Manager.disconnect();
            if(!parent.equals("free_pieces"))
            {
                emptySlots(username, parent);
            }

        } else {
            if(parent.equals("free_pieces"))
            {
                emptySlots(username, target);
                movePiece(username, parent, piece, target);
            } else {
                exchangeSlots(username, target, parent);
            }
        }



        return gameSaved(username);
    }

    public String gameSaved(String username) {
        ResultSet rs;
        String result = "no_game";
        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("select cell_1, cell_2, cell_3, cell_4, cell_5, cell_6, cell_7, cell_8, cell_9, moves from gamestates where user_id='"+username+"' ORDER BY `date` DESC");
            if (rs.first()) {
                JSONObject record = new JSONObject();
                record.put("cell_1", rs.getInt("cell_1"));
                record.put("cell_2", rs.getInt("cell_2"));
                record.put("cell_3", rs.getInt("cell_3"));
                record.put("cell_4", rs.getInt("cell_4"));
                record.put("cell_5", rs.getInt("cell_5"));
                record.put("cell_6", rs.getInt("cell_6"));
                record.put("cell_7", rs.getInt("cell_7"));
                record.put("cell_8", rs.getInt("cell_8"));
                record.put("cell_9", rs.getInt("cell_9"));
                record.put("moves", rs.getInt("moves"));
//                System.out.println(record);
                result = record.toString();
            }
            rs.close();
            Manager.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }

        if(result.equals("no_game"))
        {
            System.out.println(username+": NO GAME, CREATE A NEW ONE");
            newGame(username);
            result = gameSaved(username);
        }
        return result;
    }
}
