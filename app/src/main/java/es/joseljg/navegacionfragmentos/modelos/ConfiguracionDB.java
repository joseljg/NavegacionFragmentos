package es.joseljg.navegacionfragmentos.modelos;

public class ConfiguracionDB {
	public static final String HOSTDB = "10.0.2.2";
	//public static final String HOSTDB = "192.168.1.160";

	public static final String NOMBREDB = "ciudadesdb";

	public static final String USUARIODB = "root";
	//  public static final String USUARIODB = "damserver1";

	public static final String CLAVEDB = "1234";
	// public static final String CLAVEDB = "dam1234";

	// no funcionan las dos ultimas opciones
	// private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";

	private static final String OPCIONES = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String PUERTOMYSQL = "3306";
	 public static final String URLMYSQL = "jdbc:mysql://"+ HOSTDB + ":" + PUERTOMYSQL+"/" + NOMBREDB + OPCIONES;

	//----------------------------------------------------------....
}
