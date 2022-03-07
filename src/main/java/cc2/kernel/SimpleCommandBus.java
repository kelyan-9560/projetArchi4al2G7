package cc2.kernel;



public class SimpleCommandBus implements CommandBus {
    @Override
    public <C extends Command, R> R send(C command) {
        return null;
    }
}
