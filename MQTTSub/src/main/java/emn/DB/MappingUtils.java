package emn.DB;

public class MappingUtils {
	
	public static int measureTypeStringToInt(String type)
	{
		switch (type) {
		case "temperature":
			return 1;
		case "humidite":
			return 2;
		case "pression":
			return 3;
		default:
			return -1;
		}
	}
}
