#include<string>
#include<iostream>
using namespace std;
int main()
{
	string s = " [[  -67.275,17.947], [-65.606,17.947]], \n  [[ -65.606,18.515], [ -67.275,18.515]], ";
	double leftj = -67.275, rightj = -65.606;
	double topw = 18.515, buttomw = 17.947;
	double intervalv = (rightj - leftj) / 35;
	double intervalh = (topw - buttomw) / 12;
	for (double i = 1; i < 35.0; i+=1.0)
	{
		s += "\n[[" + to_string(rightj - intervalv * i)+",18.515],["+ to_string(rightj - intervalv * i)+",17.947]],";
	}
	for (double i = 1.0; i < 12; i += 1.0)
	{
		s+= "\n[[-67.275,"+to_string(buttomw+intervalh*i)+"],[-65.606,"+ to_string(buttomw + intervalh * i) + "]],";
	}
	cout << s;
	system("pause");
}
