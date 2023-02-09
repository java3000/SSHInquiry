package providers;

import Dto.Device;
import Dto.peripheral.Peripheral;
import Dto.software.Installation;
import Dto.subdevice.Subdevice;
import com.jcabi.ssh.Shell;
import com.jcabi.ssh.SshByPassword;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SshProvider implements Provider {

    private final Device currentDevice;
    private final Shell ssh;
    private final String host;
    private final String login;
    private final String password;


    //region SSH commands
    private final static String MEMORY_DMIDECODE = "sudo dmidecode -t 17";
    //endregion

    public SshProvider(String host, String login, String password) throws UnknownHostException {
        this.host = host;
        this.login = login;
        this.password = password;

        this.currentDevice = new Device();
        this.ssh = new SshByPassword(host, 22, login, password);
    }

    @Override
    public Device inquireDevice() {
        /*inquireAudioCard();
        inquireCddvdDrive();
        inquireController();
        inquireDiskDrive();
        inquireDisketteDrive();
        inquireKeyboard();
        inquireLogicalDrive();*/
        inquireMemory();
        /*inquireModem();
        inquireMonitor();
        inquireMotherboard();
        inquireNetworkAdapter();
        inquirePointingDevice();
        inquirePrinter();
        inquireProcessor();
        inquireVideoAdapter();
        inquireSoftware();*/


        return currentDevice;
    }

    @Override
    public List<Subdevice> inquireAudioCard() {
        return null;
    }

    @Override
    public List<Subdevice> inquireCddvdDrive() {
        return null;
    }

    @Override
    public List<Subdevice> inquireController() {
        return null;
    }

    @Override
    public List<Subdevice> inquireDiskDrive() {
        return null;
    }

    @Override
    public List<Subdevice> inquireDisketteDrive() {
        return null;
    }

    @Override
    public List<Peripheral> inquireKeyboard() {
        return null;
    }

    @Override
    public List<Subdevice> inquireLogicalDrive() {
        return null;
    }

    //Memory Device
    //	Array Handle: 0x0020
    //	Error Information Handle: 0x0023
    //	Total Width: 64 bits
    //	Data Width: 64 bits
    //	Size: 4 GB
    //	Form Factor: SODIMM
    //	Set: None
    //	Locator: Bottom-slot 1(left)
    //	Bank Locator: CHANNEL A
    //	Type: DDR4
    //	Type Detail: Synchronous Unbuffered (Unregistered)
    //	Speed: 1866 MT/s
    //	Manufacturer: Unknown ID:0x0100
    //	Serial Number: 00000000
    //	Asset Tag: Not Specified
    //	Part Number: R744G2606S1S
    //	Rank: 1
    //	Configured Memory Speed: 1866 MT/s
    //	Minimum Voltage: 1.2 V
    //	Maximum Voltage: 1.2 V
    //	Configured Voltage: 1.2 V
    @Override
    public List<Subdevice> inquireMemory() {
        var rawResult = execSudoCommand(MEMORY_DMIDECODE);
        var parsed = parseDmiDecodeMemory(rawResult);

        List<Subdevice> result = new ArrayList<>();

        for (var item : parsed) {
            //
        }
        return null;
    }

    private List<Map<String, String>> parseDmiDecodeMemory(String rawResult) {
        var splitted = rawResult.lines()
                .skip(5)
                .filter(x -> !x.contains("DMI"))
                .filter(x -> !x.contains("Memory Device"))
                .toList();

        List<Map<String, String>> finalList = new ArrayList<>();
        Map<String, String> pairs = new HashMap<>();

        for (var x : splitted) {
            if (!x.isEmpty()) {
                var pair = x.split(":");
                pairs.put(pair[0].strip(), pair[1].strip());
            } else {
                finalList.add(new HashMap<>(pairs));
                pairs.clear();
            }
        }
        return finalList;
    }

    private String execSudoCommand(String command) {
        try {
            var outputStream = new ByteArrayOutputStream();
            ssh.exec("sudo -S -p '' " + command,
                    new ByteArrayInputStream(password.getBytes(StandardCharsets.UTF_8)),
                    outputStream,
                    System.err);
            return outputStream.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Subdevice> inquireModem() {
        return null;
    }

    @Override
    public List<Peripheral> inquireMonitor() {
        return null;
    }

    @Override
    public List<Subdevice> inquireMotherboard() {
        return null;
    }

    @Override
    public List<Subdevice> inquireNetworkAdapter() {
        return null;
    }

    @Override
    public List<Peripheral> inquirePointingDevice() {
        return null;
    }

    @Override
    public List<Peripheral> inquirePrinter() {
        return null;
    }

    @Override
    public List<Subdevice> inquireProcessor() {
        return null;
    }

    @Override
    public List<Subdevice> inquireVideoAdapter() {
        return null;
    }

    @Override
    public List<Installation> inquireSoftware() {
        return null;
    }
}
