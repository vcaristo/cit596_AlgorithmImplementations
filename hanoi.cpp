#include <iostream>
#include <math.h>

using namespace std;

/**
 * Implementatation of two variations of the classic Towers of Hanoi recursive algorithm.
 * 
 * Outputs the number of operations performed by each variation. 
 * 
 * Running time: O(2^n) (both variations)
*/


/*
*  Single discs moved in O(1)-time
*/
int hanoi(int a,int b, int c, int n);

/*
* ith smallest disc takes O(i) time to move
*/
int hanoi2(int a, int b, int c, int n, int i);

// counters to track running time
int count;
int count2;

int main(){

    int n,a,b,c;

    while (1==1){

        count=0;
        count2=0;

        cout << "number of disks (n): ";
        cin >> n;

        hanoi(a,b,c,n);
        cout << "\nHanoi T(n) = " << count << endl;
        cout << "2^n -1 = " << pow(2,n) - 1 << endl;

        hanoi2(a,b,c,n,n);
        cout << "\nHanoi2 T(n) = " << count2 << endl;
        cout << "2^(n+1) -n -2 = " << pow(2,n+1) - n - 2 << endl << endl;
        cout << "-----------------------------\n";
    }

    return 0;
    
}

/*
*  Moves n discs from a to c, with b as intermediary
*  Time to move a single disc is O(1)
*/
int hanoi(int a,int b, int c, int n){
    if (n == 1){
        count=count+1;
    }

    else{
        hanoi(a,c,b,n-1);
        hanoi(a,b,c,1);
        hanoi(b,a,c,n-1);
    }

    return 0;
}

/*
*  Moves n discs from a to c, with b as intermediary
*  Time to move a single disc is O(i)
*/
int hanoi2(int a, int b, int c, int n, int i){
    if (n == 1){
        count2=count2 + i;
    }

    else{
        hanoi2(a,c,b,n-1,i-1);
        hanoi2(a,b,c,1, i);
        hanoi2(b,a,c,n-1,i-1);
    }

    return 0;

}
