/**
 * @file 1.c
 * @brief	strcmpを自前で作りなさい。関数名は「mystrcmp」とする。
 *			ただし、ループ構造使用禁止。
 * @author Lee Hyeongbeen
 * @date 2024/2/22
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>

#define TIMES 2			/* 入力回数の設定 */
#define SIZE 5			/* 入力桁数の設定 */

/* 関数宣言 */
void toEmpty(char *input);
void inputNumber(char inputs[][SIZE] );


/** 
 * 文字列の比較（≒strcmp）
 * @param (*s1) 	1番目文字列の文字
 * @param (*s2) 	2番目文字列の文字
 * @return 			strcmpの結果
*/
void mystrcmp(const char *s1, const char *s2) {
	
	
	if (*s1 == '\0' && *s2 == '\0') {				/* 2つの文字列が一致したまま、尾の'\0'にたどり着いた時 */
		printf("二つの文字列が一致しています。");
		
	} else if (*s1 < *s2) {							/* 1番目文字列の文字 < 2番目文字列の文字 の場合*/
		printf("2番目の文字列がもっと大きいです。");
		
	} else if (*s1 > *s2) {							/* 1番目文字列の文字 > 2番目文字列の文字 の場合*/
		printf("1番目の文字列がもっと大きいです。");
	
	} else {										/* 各文字列の次の桁にある文字をパラメータとして、再帰関数を行う */
		mystrcmp(s1+1,s2+1);
	}
 
}


/* メイン */
int main(void){	
	
	
	char inputs[TIMES+1][SIZE]= {0};		/* 保管配列 */
	
	inputNumber(inputs);					/* 入力開始及びoutputNumber実施 */
	
	
	printf("\n【結果】\n");
	
	mystrcmp(inputs[0],inputs[1]);			/* 文字列の比較 */
	
	printf("\n\n【終了】\n");

	getchar();
	return 0;
}


/** 
 * 文字の入力
 * @param (*inputs) 保管配列
 * @return 			文字の入力
*/
void inputNumber(char inputs[][SIZE] ){
	
	int i=0;				/* 入力桁の添え数 				*/
	int j=0;				/* 入力回数の添え数  			*/
	
	while (j<TIMES ) {		/* 入力回数設定の内  */
		
		printf("\n\n%d番目の文字列を入力してください\n>>",j+1);
		
		
		while (1) {						/* 入力が正しい時まで */
			inputs[j][i] = getchar();
			
			if (inputs[j][i] == '\n') {			/* 文字列の尾に着いたら */
				if (i == 0) {					/* 未入力の場合、再入力に戻る */
					printf("\nエラー発生\n未入力です。入力し直してください\n>>");
					inputs[j][i] = '\0';		/* 最後入力値を数字に変換 */
					continue;
				} else{
					printf("%d番目の文字列 : %s\n", j+1, inputs[j]);	/* 正常の場合、次の入力に進む */
					j++;
					i = 0;
					break;
				}
		   	} else if (i > SIZE-1 ) {			/* 入力範囲から外れた時、再入力を求める */
				printf("\nエラー発生\n半角の%d字以内で入力し直してください\n>>", SIZE);
				toEmpty(inputs[j]);
				while (getchar() != '\n');
				i = 0;
				continue;
			} else {
				i++;
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

