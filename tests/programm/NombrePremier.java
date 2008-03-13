public class NombrePremier {

	private static final int MAXVALUE = 300000;
	
	public static void main(String[] args) {

		for(int index = 0; index<MAXVALUE;index++)
			{
			System.out.println(index + " est premier : " + isPrime(index));
			}
	}
	
	private static boolean isPrime(int number)
	{
			int i=2;
			// Inférieur à 2 : pas premier
		  	if (number<2)
		  	{
		  		return false;
		  	}
			// Egal 2 : premier
			if(number == 2)
			{
				return true;
			}
			// Nombre pair autre que 2 : n'est pas premier
			if((number % 2) == 0)
			{
				return false;
			}

			while((i*i) <= number)
			{
				if((number % i) == 0)
				{
					return false;
				}
				i+=2;
			}
			return true;
		
				//Fin if
			//Fin if
//		Fin function

	}
}
