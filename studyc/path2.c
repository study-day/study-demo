#include<stdio.h>


int main1(void) {
	char input[100] = "D:\\test\\test";
	for (int i = 0; i < strlen(input); i++)
	{

		if (input[i] == '\\') {
			input[i] = '/';
		}
	}
	printf("%s", input);
	printf("%s", "D:/test/test123");
	return 0;
}
int main(int argc, char** argv)
{
	//char input[100] = "D:\\test\\test";
	for (int i = 0; i < strlen(argv[1]); i++)
	{

		if (argv[1][i] == '\\') {
			argv[1][i] = '/';
		}
	}
	printf("%s", argv[1]);
	return 0;
}

 
#include<stdio.h>

int main2(int argc, char* argv[])
{
	int i;
	printf("Total %d arguments\n", argc);
	for (i = 0; i < argc; i++)
	{
		printf("Argument %d = %s\n", i + 1, argv[i]);
	}

	system("pause");
	return 0;
}