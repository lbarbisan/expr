#include <stdio.h>

#define MAXVALUE 300000

int isPrime(int number)
{
		int i=2;
		// Inférieur à 2 : pas premier
		 if (number<2)
		 {
		  	return 0;
		 }
		// Egal 2 : premier
		if(number == 2)
		{
			return 1;
		}
		// Nombre pair autre que 2 : n'est pas premier
		if((number % 2) == 0)
		{
			return 0;
		}

		while((i*i) <= number)
		{
			if((number % i) == 0)
			{
				return 0;
			}
			i+=2;
		}
	return 1;
}

int main(char** args, int argv)
{

	int index = 0;
		for(index; index<MAXVALUE;index++)
			{
			printf("%d est premier : %d", index , isPrime(index));
			}
	return 0;
}

