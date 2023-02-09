import providers.Provider;
import providers.SshProvider;

import java.net.UnknownHostException;

public class Application {
    public static void main(String[] args) {

        try {
            Provider provider = new SshProvider("127.0.0.1", "odmin", "2116842@adfE");
            provider.inquireDevice();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
