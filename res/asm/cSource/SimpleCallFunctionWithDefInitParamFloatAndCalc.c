#include <stdio.h>

void function(int c, float e)
{
	int a;
	int b = 0;
	a = (float)(b)+9.0 * e /(float)c;
	return;
}

int main (char * argv, int argn)
{
	function(1,2.0);
	return 0;
}
