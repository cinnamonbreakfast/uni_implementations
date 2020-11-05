package GUI;

import Controller.Controller;
import Model.Containers.*;
import Model.ProgramState;
import Model.Statements.IStatement;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.*;
import View.Programs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;

public class MainWin {
    private Stage mainStage;
    private Programs progList;

    @FXML
    private ListView<String> lv;

    @FXML
    private Button runbtn;

    @FXML
    public void initialize() {
        List<String> ex = Arrays.asList("Prog1", "Prog2", "Prog3", "Prog4", "Prog5", "Prog6", "Prog7", "Prog8", "Prog9", "Prog10", "Prog11");
        ObservableList<String> programs = FXCollections.observableArrayList(
                "Prog1", "Prog2", "Prog3", "Prog4", "Prog5", "Prog6", "Prog7", "Prog8", "Prog9", "Prog10", "Prog11");
        lv.setItems(programs);
        lv.setOnMouseClicked(e -> selectedItem());

        runbtn.setOnAction(e -> runProgram());

        progList = new Programs();
    }

    public Controller loadProgram(int index)
    {
        IStack<IStatement> stack1 = new MyStack<IStatement>();
        IHeap<Value> heap1 = new MyHeap<Value>();
        IDictionary<String, Value> symTable1 = new MyDictionary<String, Value>();
        IDictionary<StringValue, BufferedReader> fileTable1 = new MyDictionary<StringValue, BufferedReader>();
        IList<Value> out1 = new MyList<Value>();

        IStatement ex1 = progList.getProgram(index);

        try {
            MyDictionary<String, Type> typeEnv = new MyDictionary<>();

            ex1.typeCheck(typeEnv);
        } catch (Exception ex)
        {
            System.out.println("[TypeChecking] On program " +ex.getMessage());
        }

        ProgramState prg1 = new ProgramState(stack1, heap1, symTable1, fileTable1, out1, ex1);
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctrl1 = new Controller(repo1);

        return ctrl1;
    }

    private void selectedItem()
    {
        System.out.println(lv.getSelectionModel().getSelectedIndex());
    }

    private void runProgram()
    {
        int index = lv.getSelectionModel().getSelectedIndex();
        if(index >= 0)
        {
            // run program
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Debugger.fxml"));
                AnchorPane newLayout = loader.load();

                Debugger db = loader.getController();
                db.setProgram(progList.getProgram(index+1));
                db.setProgramIndex(index+1);
                db.loadProgram();

                Scene newScene = new Scene(newLayout);

                Stage newStage = new Stage();
                newStage.setTitle("Program state");
                newStage.setScene(newScene);

                newStage.initModality(Modality.WINDOW_MODAL);
                newStage.initOwner(mainStage);

                newStage.show();
            } catch (Exception e)
            {
                System.out.println(Arrays.toString(e.getStackTrace()));
            }

        } else {
            // nothing selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No program selected to run.");

            alert.showAndWait();
        }
    }

}
