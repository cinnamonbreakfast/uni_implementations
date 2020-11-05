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
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.util.*;
import java.util.stream.Collectors;

public class Debugger {

    private int pState;
    private int s;
    private int curPrg;
    private IStatement prog;
    private Controller ctrl;
    private ProgramState prg;

    IStack<IStatement> stack;
    IHeap<Value> heap;
    IDictionary<String, Value> symTable;
    IDictionary<StringValue, BufferedReader> fileTable;
    IList<Value> out;
    MySemaphore semaphoreTable;
    MyBarrier barrierTable;

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
    @FXML TableColumn<Map.Entry<Integer, Value>, String> addressV;

    // Semaphore
    @FXML TableView<Triplet<Integer, List<Integer>, Integer>> semaphoreT;
    @FXML TableColumn<Triplet<Integer, List<Integer>, Integer>, Integer> SphIndex;
    @FXML TableColumn<Triplet<Integer, List<Integer>, Integer>, Integer> SphValue;
    @FXML TableColumn<Triplet<Integer, List<Integer>, Integer>, List<Integer>> SphList;
    // Semaohore END

    // Barrier
    @FXML TableView<Map.Entry<Integer, Pair<Integer, List<Integer>>>> BarrierT;
    @FXML TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> BarIndex;
    @FXML TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, Integer> BarValue;
    @FXML TableColumn<Map.Entry<Integer, Pair<Integer, List<Integer>>>, List<Integer>> BarList;
    // Barrier END


    @FXML ListView<Integer> PrgIdent;

    @FXML
    public void initialize()
    {
        curPrg = 0;
        varNames.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey() + ""));
        values.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));

        addressC.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getKey()));
        addressV.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()+" "));

        // Semaphore
        SphIndex.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getFirst()));
        SphValue.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getThird()));
        SphList.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getSecond()));
        // Semaphore END

        // Barrier
        BarIndex.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getKey()));
        BarValue.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue().getKey()));
        BarList.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue().getValue()));
        // Barrier END


        // Setting up program execution

        stack = new MyStack<IStatement>();
        heap = new MyHeap<Value>();
        symTable = new MyDictionary<String, Value>();
        fileTable = new MyDictionary<StringValue, BufferedReader>();
        out = new MyList<Value>();
        semaphoreTable = new MySemaphore();
        barrierTable = new MyBarrier();

        // END

        List<String> ex = new ArrayList<String>();

        codeView.getSelectionModel().select(s++);
        step.setOnAction(e -> oneStep());

        PrgIdent.setOnMouseClicked(e -> selectedProgram());

    }

    private void selectedProgram()
    {
        curPrg = PrgIdent.getSelectionModel().getSelectedIndex();
        IStack<IStatement> stack = ctrl.getRepo().getProgramStates().get(curPrg).getExeStack();
        List<String> stackOutput = new ArrayList<>();
        for (IStatement stm : stack.getStack()){
            stackOutput.add(stm.toString());
        }
        Collections.reverse(stackOutput);
        codeView.setItems(FXCollections.observableArrayList(stackOutput));
    }

    public void oneStep()
    {


        ctrl.executeOneStep();

        if(((Repository)ctrl.getRepo()).stillExec())
        {
            populatePrgs();
            populateOut();
            populateSymbol();
            populateHeap();
            populateExe();
            populateSemaphore();
            populateBarrier();

        } else {
            populateOut();
            populateSymbol();
            populateHeap();
            populateExe();
            step.setDisable(true);
//            return;
        }

    }

    private void populateBarrier()
    {
        List<Map.Entry<Integer, Pair<Integer, List<Integer>>>> barrierListed =
                new ArrayList<>(ctrl.getRepo().getProgramStates().get(curPrg).getBarrierTable().getBarriers().getMap().entrySet());
        BarrierT.setItems(FXCollections.observableList(barrierListed));
        BarrierT.refresh();
    }

    private void populateSemaphore()
    {
        List<Triplet<Integer, List<Integer>, Integer>> symbolTableListed =
                new ArrayList<>(ctrl.getRepo().getProgramStates().get(curPrg).getSemaphoreTable().getSemaphore().getMap().values());
        semaphoreT.setItems(FXCollections.observableList(symbolTableListed));
        semaphoreT.refresh();
    }

    private void populatePrgs()
    {
        PrgIdent.setItems(FXCollections.observableArrayList(ctrl.getRepo().getProgramStates().stream().map(ProgramState::getID).collect(Collectors.toList())));
        PrgIdent.refresh();
    }

    private void populateExe()
    {
        if(ctrl.getRepo().getProgramStates().size() == 0)
        {
            codeView.getItems().clear();
            return;
        }
        // todo: gresit
        IStack<IStatement> stack = ctrl.getRepo().getProgramStates().get(ctrl.getRepo().getProgramStates().size()-1).getExeStack();
        List<String> stackOutput = new ArrayList<>();
        for (IStatement stm : stack.getStack()){
            stackOutput.add(stm.toString());
        }
        Collections.reverse(stackOutput);
        codeView.setItems(FXCollections.observableArrayList(stackOutput));
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
        prg = new ProgramState(stack, heap, symTable, fileTable, out, semaphoreTable, barrierTable, prog);
        IRepository repo = new Repository(prg, "log1.txt");
        ctrl = new Controller(repo);

        System.out.println(prog);

        IStack<IStatement> stack = prg.getExeStack();
        List<String> stackOutput = new ArrayList<>();
        for (IStatement stm : stack.getStack()){
            stackOutput.add(stm.toString());
        }
        Collections.reverse(stackOutput);
        codeView.setItems(FXCollections.observableArrayList(stackOutput));

        populatePrgs();
        populateOut();
        populateSymbol();
        populateHeap();
        //populateExe();
    }

}
