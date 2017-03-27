package ApacheMINA;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class ApacheMINA {
    private static final int PORT = 3333;

    public static void main(String[] args) throws Exception {
        NioSocketAcceptor acceptor = new NioSocketAcceptor();
        DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
        MdcInjectionFilter mdcInjectionFilter = new MdcInjectionFilter();
        chain.addLast("mdc", mdcInjectionFilter);

        acceptor.setHandler(new ChatProtocolHandler());
        acceptor.bind(new InetSocketAddress(PORT));
        System.out.println("Listening on port " + PORT);
    }
}
