package cc2.kernel;

public class SimpleQueryBus implements QueryBus{
    @Override
    public <Q extends Query, R> R send(Q query) {
        return null;
    }
}
