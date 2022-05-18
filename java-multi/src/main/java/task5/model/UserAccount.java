package task5.model;

import lombok.Data;
import task5.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

@Data
public class UserAccount {
    private String fullName;
    private BigDecimal UAHBalance;
    private BigDecimal USDBalance;
    private String filePath;

    public UserAccount(String fullName, BigDecimal UAHBalance) throws IOException {
        this.fullName = fullName;
        this.UAHBalance = UAHBalance;
        this.USDBalance = BigDecimal.ZERO;
        this.filePath = Utils.USER_FILE_PACKAGE + fullName + ".txt";

        File userFile = new File(filePath);
        System.out.printf("%s with balance %d created!\n", fullName, UAHBalance.intValue());
    }
}
