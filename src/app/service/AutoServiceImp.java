package app.service;

import app.domain.Auto;
import app.repository.AutoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class AutoServiceImp implements AutoService {

    private AutoRepository autoRepository;
    private String sticker;

    public AutoServiceImp(AutoRepository autoRepository, @Value("${car.sticker}") String sticker) {
        this.autoRepository = autoRepository;
        this.sticker = sticker;
    }


    @Override
    public Auto getAuto(Long id) {
        Auto auto = autoRepository.getAuto(id);
        setSticker(auto);
        return auto;
    }


    private void setSticker(Auto auto) {
        String autoSticker = String.format("%s-%s-%s-%s", sticker, auto.getBrand().charAt(0), auto.getModel().charAt(0), auto.getNumber());
        auto.setSticker(autoSticker);
    }
}
