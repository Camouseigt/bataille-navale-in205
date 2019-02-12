
public class Sleep {
	private static void Sleep(int ms) {
		try {
		Thread.sleep(ms);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
		}
}
