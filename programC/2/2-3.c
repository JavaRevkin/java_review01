/**
 * @file 2-3.c
 * @brief	１〜１０００の間にある素数をすべて表示するプログラムを作成せよ。
			１、２にとらわれず早いアルゴリズムを作成せよ。
 * @author Lee Hyeongbeen
 * @date 2024/2/27
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>

#define TIMES 2			/* 入力回数の設定 	*/
#define SIZE 4			/* 入力桁の制限		*/
#define NUM1 1			/* 【１〜１０００の間】の1 		*/
#define NUM2 1000		/* 【１〜１０００の間】の1000 	*/



/* 関数宣言 */
void prime(void) {
	
	int i;					/* 添え数 */
	int count = 0;			/* 素数判断のカウント 	*/
	int num1 = NUM1;		/* 1の数字の初期化 		*/
	int num2 = NUM2;		/* 1000の数字の初期化 	*/
	
	if (num1 > num2) {		/* 下のコーディングため、必ずnum2がnum1より大きくさせるべき	*/
		i = num1;
		num1 = num2;
		num2 = i;
	}
	printf("\n-----------%dと%dの間の素数出力----------------\n\n",NUM1,NUM2);
	
	for ( ; num1 < num2 ; num1++) {			/* num1とnum2の間まで、num1+1から開始 		*/
		for (i = 2 ; i < num1 ; i++) {		/* iを2からnum1前まで伸ばし続ける			*/
			if (num1 % i == 0) {			/* num1が素数であった場合、iとの比較終了	*/
				break;
			}
		}
		if (num1 == i) {					/* 素数だた判断され、for文から逃れた場合	*/
			printf ("%d\t",num1);			/* 出力					*/
			if (++count % 6 == 0) {			/* 出力6個単位で改行	*/
				printf("\n");
			}
		}
	}

	if (count) {							/* 素数があった場合、数を出力 */
		printf("\n\n【結果】%d個の素数があります。", count);	
			
	} else {								/* 2つの数字の間に素数がなかった場合 */
		printf("\n\n【結果】素数がありませんでした。");		
	}
	printf("\n-------------------------------------------------\n");		
}


/* メイン */
int main(void){	
	
	
	prime();		/* 素数確認			*/
	
	
	printf("\n\n【終了】\n");
	
	getchar();
	return 0;
}
