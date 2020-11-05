package View;

import Controller.Controller;
import Model.Exceptions.MyException;

public class RunExample extends Command {
    private Controller ctrl;

    public RunExample(String key, String description, Controller ctrl) {
        super(key, description);
        this.ctrl = ctrl;
    }

    @Override
    public void execute() {
        try
        {
            ctrl.allStep();
        }
        catch (MyException | InterruptedException e)
        {
            System.out.println("\n\n\nExec " +  e.getMessage()+"\n\n\n");
        }
    }
}
