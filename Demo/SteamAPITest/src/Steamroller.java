import com.github.koraktor.steamcondenser.exceptions.WebApiException;
import com.github.koraktor.steamcondenser.steam.community.WebApi;


public class Steamroller extends WebApi {
	public Steamroller(String apiKey) throws WebApiException {
		this.setApiKey(apiKey);
	}
}
