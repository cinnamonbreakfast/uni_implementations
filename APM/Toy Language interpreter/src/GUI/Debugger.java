package GUI;

import Controller.Controller;
import Model.Containers.*;
import Model.ProgramState;
import Model.Statements.IStatement;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Debugger {

    private int pState;
    private int s;
    private IStatement prog;
    private Controller ctrl;
    private ProgramState prg;

    IStack<IStatement> stack;
    IHeap<Value> heap;
    IDictionary<String, Value> symTable;
    IDictionary<StringValue, BufferedReader> fileTable;
    IList<Value> out;

    @FXML
    ListView<String> codeView;

    @FXML
    Button step;

    @FXML
    TextArea output;

    @FXML TableView <Map.Entry<String, Value>> symbolT;
    @FXML TableColumn<Map.Entry<String, Value>, String> varNames;
    @FXML TableColumn<Map.Entry<String, Value>, Value> values;

    @FXML TableView <Map.Entry<Integer, Value>> heapT;
    @FXML TableColumn<Map.Entry<Integer, Value>, Integer> addressC;
    @FXML TableColumn<Map.Entry<Integer, Value>, Value> addressT;

    @FXML
    public void initialize()
    {
        varNames.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey() + ""));
        values.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));

//        addressC.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getKey()));
//        addressT.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));

        // Setting up program execution

        stack = new MyStack<IStatement>();
        heap = new MyHeap<Value>();
        symTable = new MyDictionary<String, Value>();
        fileTable = new MyDictionary<StringValue, BufferedReader>();
        out = new MyList<Value>();

        // END

        List<String> ex = new ArrayList<String>();

        //String[] arrOfStr  = prog.toString().split(";", 2);

        //ex.addAll(Arrays.asList(arrOfStr));
        //ObservableList<String> programs = FXCollections.observableArrayList(ex);


        //List<String> ex = Arrays.asList("Juliana", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
        //ObservableList<String> programs = FXCollections.observableArrayList(
        //        "Juliana", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");
        //codeView.setItems(programs);

        s = -1;

        codeView.getSelectionModel().select(s++);
        step.setOnAction(e -> oneStep());

    }

    public void oneStep()
    {
        codeView.getSelectionModel().select(s++);
        System.out.println(s);

        prg.oneStep();
        if(!(codeView.getItems().size() % 2 == 1 && s == codeView.getItems().size()))
            prg.oneStep();

        System.out.println(prg.noSteps());

        if(prg.noSteps())
            step.setDisable(true);

        populateOut();
        populateSymbol();
    }

    private void populateSymbol()
    {
        IDictionary<String, Value> symbolTable = prg.getSymTable();
        List<Map.Entry<String, Value>> symbolTableListed = new ArrayList<>(symbolTable.getMap().entrySet());
        symbolT.setItems(FXCollections.observableList(symbolTableListed));
        symbolT.refresh();
    }

    private void populateHeap()
    {
        IHeap<Value> heap = prg.getHeap();
        List<Map.Entry<Integer, Value>> symbolTableListed = new ArrayList<>(heap.getContent().entrySet());
        heapT.setItems(FXCollections.observableList(symbolTableListed));
        heapT.refresh();
    }

    private void populateOut()
    {
        String out = prg.getOut().toString();

        output.setText(out);
    }

    public void setProgram(IStatement prg)
    {
        prog = prg;

        try {
            MyDictionary<String, Type> typeEnv = new MyDictionary<>();

            prg.typeCheck(typeEnv);
        } catch (Exception ex)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Type error");
            alert.setHeaderText(null);
            alert.setContentText("Type checking has failed. Check your code.");

            alert.showAndWait();
        }
    }

    public void setProgramIndex(int in)
    {
        pState = in;
    }

    public void loadProgram()
    {
        prg = new ProgramState(stack, heap, symTable, fileTable, out, prog);
        IRepository repo = new Repository(prg, "log1.txt");
        ctrl = new Controller(repo);

        System.out.println(prog);

        List<String> ex = new ArrayList<String>();

        String[] arrOfStr  = prog.toString().replace(" ","").replace("\t","").replace("\n","").split(";");

        ex.addAll(Arrays.asList(arrOfStr));
        ObservableList<String> programs = FXCollections.observableArrayList(ex);

        codeView.setItems(programs);
    }

}
