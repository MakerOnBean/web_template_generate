package cloud.makeronbean.generate.starter.base.starter;

/**
 * @author makeronbean
 * @createDate 2023-05-04  09:38
 * @description
 */
public class StarterBridgeAdapterImpl implements StarterBridgeAdapter{
    @Override
    public void loadStarter(AbstractStarter starter) {
        starter.load();
    }
}
