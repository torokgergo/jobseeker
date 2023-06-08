package hu.gergo.jobseeker.helper;

import hu.gergo.jobseeker.model.Position;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PositionHelper {

    private static String BASE_PATH;

    @Value("${base.path}")
    public void setBasePath(String basePath){
        PositionHelper.BASE_PATH = basePath;
    }

    public static String getPositionUrl(Position position) {
        return PositionHelper.BASE_PATH + "/position/" + position.getId();
    }
}
