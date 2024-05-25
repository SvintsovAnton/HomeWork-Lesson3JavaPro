package app.repository;

import app.domain.Auto;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AutoRepositoryImpl implements AutoRepository {

    //решение через List

    /*
    List<Auto> listWithAuto = new ArrayList<Auto>();

    public AutoRepositoryImpl() {
        listWithAuto.add(new Auto(1L,"Audi","A5","MAD580",2020,45600.5D));
        listWithAuto.add(new Auto(2L,"BMW","X1","MTA289",2019,46981.8D));
        listWithAuto.add(new Auto(3L,"Volvo","XC40","MRT777",2021,87546.1D));
        listWithAuto.add(new Auto(4L,"VW","Passat","MTN999",2023,1600.9D));
    }

    @Override
    public Auto getAuto(Long id) {
        for (Auto auto:listWithAuto){
            if (auto.getId().equals(id)){
                return auto;
            }
        }
        return null;
    }
*/

    //решение через файл
    //данные при запуске записываются в файл database.txt, откуда потом идет четние при поиске
    //при создании метода getAuto использовал два дополнительных метода(checkAutoIdInStream и makeAutoFromString) для более читабельного кода


    File database = new File("C:\\Users\\svint\\IdeaProjects\\HomeWorkLesson3\\resources\\database.txt");

    public AutoRepositoryImpl() {
        if (!database.exists()) {
            try {
                database.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(database, true));) {
            writer.write(new Auto(1L, "Audi", "A5", "MAD580", 2020, 45600.5D).toString() + "\n");
            writer.write(new Auto(2L, "BMW", "X1", "MTA289", 2019, 46981.8D).toString() + "\n");
            writer.write(new Auto(3L, "Volvo", "XC40", "MRT777", 2021, 87546.1D).toString() + "\n");
            writer.write(new Auto(4L, "VW", "Passat", "MTN999", 2023, 1600.9D).toString() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Auto getAuto(Long id) {
        String autoToString;
        Auto auto;
        if (database.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(database))) {
                autoToString = reader.readLine();
                if (checkAutoIdInStream(id, autoToString)) {
                    return makeAutoFromString(autoToString);
                } else {
                    while (!((autoToString = reader.readLine()) == null)) {
                        if (checkAutoIdInStream(id, autoToString)) {
                            return makeAutoFromString(autoToString);
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private boolean checkAutoIdInStream(Long id, String stringInStream) {
        String temporaryId = stringInStream.substring(stringInStream.indexOf("id- ") + "id- ".length(), stringInStream.indexOf(",", stringInStream.indexOf("id- ") + "id- ".length()));
        if (temporaryId.equals(String.valueOf(id))) {
            return true;
        }
        return false;
    }

    private Auto makeAutoFromString(String string) {
        Auto auto = new Auto(Long.parseLong(string.substring(string.indexOf("id- ") + "id- ".length(), string.indexOf(",", string.indexOf("id- ") + "id- ".length()))),
                string.substring(string.indexOf("brand - ") + "brand - ".length(), string.indexOf(",", string.indexOf("brand - ") + "brand - ".length())),
                string.substring(string.indexOf("model - ") + "model - ".length(), string.indexOf(",", string.indexOf("model - ") + "model - ".length())),
                string.substring(string.indexOf("number - ") + "number - ".length(), string.indexOf(",", string.indexOf("number - ") + "number - ".length())),
                Integer.parseInt(string.substring(string.indexOf("year - ") + "year - ".length(), string.indexOf(",", string.indexOf("year - ") + "year - ".length()))),
                Double.parseDouble(string.substring(string.indexOf("mileage - ") + "mileage - ".length(), string.indexOf(",", string.indexOf("mileage - ") + "mileage - ".length()))));

        return auto;
    }
}

