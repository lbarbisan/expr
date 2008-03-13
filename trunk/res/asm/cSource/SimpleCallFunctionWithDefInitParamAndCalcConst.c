#include <stdio.h>

char * function(int c, int e, const char * text)
{
	int a;
	int b = 0;
	a = b+9 * e /c;
	return text;
}

int main (char * argv, int argn)
{
	char text[] = "Texte";
	function(1,2, text);
	return 0;
}
