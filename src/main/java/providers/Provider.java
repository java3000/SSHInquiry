package providers;

import Dto.Device;
import Dto.peripheral.Peripheral;
import Dto.software.Installation;
import Dto.subdevice.Subdevice;

import java.util.List;

public interface Provider {
    Device inquireDevice();
    List<Subdevice> inquireAudioCard();
    List<Subdevice> inquireCddvdDrive();
    List<Subdevice> inquireController();
    List<Subdevice> inquireDiskDrive();
    List<Subdevice> inquireDisketteDrive();
    List<Peripheral> inquireKeyboard();
    List<Subdevice> inquireLogicalDrive();
    List<Subdevice> inquireMemory();
    List<Subdevice> inquireModem();
    List<Peripheral> inquireMonitor();
    List<Subdevice> inquireMotherboard();
    List<Subdevice> inquireNetworkAdapter();
    List<Peripheral> inquirePointingDevice();
    List<Peripheral> inquirePrinter();
    List<Subdevice> inquireProcessor();
    List<Subdevice> inquireVideoAdapter();
    List<Installation> inquireSoftware();
}
