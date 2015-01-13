import com.github.koraktor.steamcondenser.exceptions.WebApiException;
import com.github.koraktor.steamcondenser.steam.SteamPlayer;
import com.github.koraktor.steamcondenser.steam.community.WebApi;


public class SteamAPITest {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		Steamroller api;
		try {
			api = new Steamroller("CD11D4410D0D75AD86BEEFADC7F2C119");
		} catch (WebApiException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
