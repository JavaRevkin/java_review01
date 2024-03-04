/**
 * @file 2-1.c
 * @brief	数字を入力し、その数字が素数かどうかを判断する
			プログラムを作成せよ。
 * @author Lee Hyeongbeen
 * @date 2024/2/26
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>

#define TIMES 2					/* 入力回数の設定 */
#define SIZE 4					/* 入力桁の制限 */

/* 関数宣言 */
void toEmpty(char *input);
int onlyNum(char *input);
void inputNumber(char input[]);



/** 
 * 素数か判断
 * @param (*num) 	判別する数字
 * @return 			素数判断結果の出力
*/
void prime(int *num) {
	
	int i;			/* 添え数 */
	int count = 0;	/* 素数判断のカウント */
	
	for (i = 2 ; i < *num ; i++) {		/* 2からnum-1の数字まで 		*/
		if (*num % i == 0) {			/* num が i の数字に割れて余りが0なら、素数でないと判断 	*/
			printf("\n【結果】%dは素数ではありません。", *num);
			count++;
			break;
		}
	}
	if (!count) {						/* 素数ではないと判別する条件文に1度も引っかかたことなければ、素数だと判断 */
		printf("\n【結果】%dは素数です。", *num);		
	}
}
/* メイン */
int main(void){	
	
	int intNum;					/* 入力が正数であった場合 */	
	char input[SIZE+1]= {0};	/* 入力値の保管配列		  */
	
	
	printf("【開始】\n");	
	
	inputNumber(input);			/* 入力開始 		*/
	
	intNum = atoi(input);		/* 正数として処理 	*/
	
	printf("\n入力した数字 : %d\n",  intNum);
	
	prime(&intNum);				/* 素数確認			*/
	
	
	printf("\n\n【終了】\n");
	
	getchar();
	return 0;
}


/** 
 * 数字の入力
 * @param (input) 	保管配列
 * @return 			数字の入力
*/
void inputNumber(char input[]) {


	int i = 0;				/* 添え数 				  */

	int numCheck;			/* 数字でない文字の判別、数学記号のチェック */	
	double doubleNum;
	
	printf("\n数値を入力してください\n>>");
	
	while (1) {								/* 入力が正しい時まで */
		
		input[i] = getchar();
		
		if (input[i] == '\n') {				/* 文字列の尾に着いたら		  */
			if (i == 0) {					/* 未入力の場合、再入力に戻る */
				printf("\nエラー発生\n未入力です。入力し直してください\n>>");
				input[i] = '\0';			/* 最後入力値を数字に変換	  */
				continue;
			}
			break;							/* while文から逃れ、入力を終える */
	   	} else {
			if (i >= SIZE)  {				/* 入力桁の制限を超えた場合、再入力に戻る */
				printf("\nエラー発生\n半角 %d字で制限してます。入力し直してください\n>>",SIZE);
				toEmpty(input);
				while (getchar() != '\n');
				i = 0;
				continue;
			} else {
				
				numCheck = onlyNum(input);				/* 純数字かを判断する関数 			*/
				
				if (numCheck) {							/* 数字ではなかったら、再入力に戻る */
					printf("\nエラー発生\n半角の正数のみ入力できます。入力し直してください\n>>");
					toEmpty(input);
					while (getchar() != '\n');
					i = 0;
					continue;
				} else{
					i++;								/* 入力正常の場合、次の桁入力に進む	*/
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
 * @param (*input) 	入力分を保管している配列
 * @return 1 : true、数字じゃない文字列で判断、条件文にかかる
 * @return 0 : false、数字のみの文字列で判断、条件文にかからず
*/
int onlyNum(char *input){
	
	while (*input !=  '\0') {
		if ( !isdigit(*input) ) {
			return 1;
		}
		input++;
	}
	return 0;
}
