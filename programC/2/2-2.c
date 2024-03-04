/**
 * @file 2-2c
 * @brief	整数を2つ入力し、その間にある素数をすべて表示する
			プログラムを作成せよ。【１】のプログラムを関数化し使用すること。
 * @author Lee Hyeongbeen
 * @date 2024/2/27
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>

#define TIMES 2			/* 入力回数の設定 */
#define SIZE 4			/* 入力桁の制限 */

/* 関数宣言 */
void toEmpty(char *input);
int onlyNum(char *input);
void inputNumber(char inputs[][SIZE], int nums[TIMES] );


/** 
 * 素数か判断
 * @param (num) 	複数数字の保管列
 * @return 			素数判断結果の出力
*/
void prime(int nums[TIMES]) {
	
	int i;					/* 添え数 */
	int count = 0;			/* 素数判断のカウント 	*/
	int num1 = nums[0];		/* 1番目入力の数字 		*/
	int num2 = nums[1];		/* 2番目入力の数字 		*/
	
	if (num1 > num2) {		/* 下のコーディングため、必ずnum2がnum1より大きくさせるべき	*/
		i = num1;
		num1 = num2;
		num2 = i;
	}
	
	printf("------------------間の素数出力-------------------\n\n");
	
	for ( ++num1 ; num1 < num2 ; num1++) {			/* num1とnum2の間まで、num1+1から開始 		*/
		for (i = 2 ; i < num1 ; i++) {				/* iを2からnum1前まで伸ばし続ける			*/
			if (num1 % i == 0) {					/* num1が素数であった場合、iとの比較終了	*/
				break;
			}
		}
		if (num1 == i) {							/* 素数だた判断され、for文から逃れた場合	*/
			printf ("%4d ",num1);					/* 出力					*/
			if (++count % 10 == 0) {				/* 出力10個単位で改行	*/
				printf("\n");
			}
		}
	}
	
	if (count) {									/* 素数があった場合、数を出力 */
		printf("\n\n\n【結果】%d個の素数があります。", count);	
			
	} else {										/* 2つの数字の間に素数がなかった場合 */
		printf("\n\n\n【結果】素数がありませんでした。");		
	}
	printf("\n-------------------------------------------------\n");
	
}


/* メイン */
int main(void){	
	
	
	char inputs[TIMES+1][SIZE]= {0};		/* 保管配列 */
	int nums[TIMES] = {0};
	
	inputNumber(inputs, nums);				/* 入力開始及びoutputNumber実施 */
	
	prime(nums);							/* 素数確認			*/
	
	
	printf("\n\n【終了】\n");
	
	getchar();
	return 0;
}


/** 
 * 文字の入力
 * @param (*inputs) 保管配列
 * @param (*nums)   入力文字列を数字化した保管配列
 * @return 			文字の入力、数字で保管
*/
void inputNumber(char inputs[][SIZE], int nums[TIMES]){
	
	int i = 0;				/* 入力桁の添え数 				*/
	int j = 0;				/* 入力回数の添え数  			*/
	int numCheck;
	
	while (j < TIMES) {		/* 入力回数設定の内  */
		
		printf("\n\n%d番目の文字を入力してください\n>>",j+1);
		
		
		while (1) {						/* 入力が正しい時まで */
			inputs[j][i] = getchar();
			
			if (inputs[j][i] == '\n') {			/* 文字列の尾に着いたら */
				if (i == 0) {					/* 未入力の場合、再入力に戻る */
					printf("\nエラー発生\n未入力です。入力し直してください\n>>");
					inputs[j][i] = '\0';		/* 最後入力値を数字に変換 */
					continue;
				} else{
					nums[j] = atoi(inputs[j]);
					printf("\n%d番目の数字 : %s\n", j+1, inputs[j]);
					j++;
					i = 0;
					break;
				}
		   	} else if (i > SIZE-1 ) {			/* 入力範囲から外れた時、再入力を求める */
				printf("\nエラー発生\n半角 数字で %d以内の数字で入力し直してください\n>>", SIZE);
				toEmpty(inputs[j]);
				while (getchar() != '\n');
				i = 0;
				continue;
			} else {
				
				numCheck = onlyNum(inputs[j]);	/* 純数字かを判断する関数 			*/
				
				if (numCheck) {					/* 数字ではなかったら、再入力に戻る */
					printf("\nエラー発生\n半角の正数のみ入力できます。入力し直してください\n>>");
					toEmpty(inputs[j]);
					while (getchar() != '\n');
					i = 0;
					continue;
				} else {
					i++;						/* 入力正常の場合、次の桁入力に進む	*/
				}
			}
		}
	}
}


/** 
 * 却下された入力分を削除する関数
 *（オーバーフロー防止）
 * @param (*input) 入力分を保管している配列
 * @return (*input)配列の初期化
*/
void toEmpty(char *input){
	
	int j = 0;		/*添え数*/
	while (input[j] != '\0') {
	  input[j] = '\0';
	  j++;
	}
}


/** 
 * 数字のみかを判断する関数
 * @param (*input) 入力分を保管している配列
 * @return 1 : true、数字じゃない文字列で判断、条件文にかかる
 * @return 0 : false、数字のみの文字列で判断、条件文にかからず
*/
int onlyNum(char *input){


	while (*input !=  '\0'){
		if( !isdigit(*input) ){
			return 1;
		}
		input++;
	}
	return 0;
}
