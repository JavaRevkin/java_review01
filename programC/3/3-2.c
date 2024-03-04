/**
 * @file 3-2.c
 * @brief	５＊５のフィールドを用いて、パズルを作れ
 * 			system式を使用し、スクロールしないように制御せよ。
 * @author Lee Hyeongbeen
 * @date 2024/2/28
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>
#include <windows.h>

#define TIMES 1			/* 入力回数の設定 */
#define SIZE 2			/* 入力桁数の設定 */
#define END "zz"		/* 終了文字の設定 */
#define WIDTH  5		/* 横（2～11まで設定可能）*/
#define LENGTH 7		/* 縦（2～11まで設定可能）*/

/* 関数宣言 */
void toEmpty(char *input);
void inputNumber(char *inputs, char *width, char *length );
int checkInput(char *inputs, char *width, char *length);
void puzzle(char *inputs, int *end, char **puzzles, char *width, char*length);
void showPuzzle(char *width, char *length, char **puzzles);
void makePuzzle(char *width, char *length, char **puzzles);
int chkover(int *j, int *i);



/* メイン */
int main(void){	

	int i ;							/* 添え数 		*/
	int end = 0;					/* 終了の添え数 */
	char inputs[SIZE] = {0};		/* 保管配列 	*/
	char width[WIDTH+1] = {0};		/* フィールドの横の配列 	*/
	char length[LENGTH+1] = {0};	/* フィールドの縦の配列 	*/
	
	char** puzzles = (char**)malloc(sizeof(char*) * LENGTH);	/* パズルのボード */
	
	
	makePuzzle(width, length, &(*puzzles));						/* パズルの作成 */
	
	while (end <= 0) {											/* zz入力まで繰り返す	*/
		showPuzzle(width, length, &(*puzzles) );				/* パズルの出力 */
		inputNumber(inputs, width, length);						/* 入力			*/
		puzzle(inputs, &end, &(*puzzles), width, length);		/* パズルに反映 */
		system("cls");
	}
	
	printf("\n\n【終了】\n");
	
	for ( i = 0 ; i < LENGTH ; i++) {		/* メモリー解放 */
		free(puzzles[i]);
	}
	free(puzzles);							/* メモリー解放 */
	
	getchar();
	return 0;
}

/** 
 * 横・縦・パズルの初期化
 * @param (*width) 		横の配列
 * @param (*length) 	縦の配列
 * @param (*puzzles)	パズル
 * @return 				横・縦・パズルの初期化
*/
void makePuzzle(char *width, char *length, char **puzzles) {
	
	int i ;
	int j ;
	
	
	for ( i = 0 ; i < WIDTH ; i++ ) {	/* 97 = a、WIDTHの数字までアルファベット順で入力	*/
		width[i] = 97+i;
	} 
	for ( i = 0 ; i < LENGTH ; i++ ) {  /* 65 = A、LENGTHの数字までアルファベット順で入力	*/
		length[i] = 65+i;
	}
	width[WIDTH] = length[LENGTH] = 'z';/*	どちらも'z'の値を配列尾に持つ（終了の判別）*/
	
	
	for ( i = 0 ; i < LENGTH ; i++) {					/* 縦の数の分 			*/
		*(puzzles+i) = (char*)malloc(WIDTH *sizeof(char) );	/* puzzle[i]の宣言		*/
		for ( j = 0 ; j < WIDTH ; j++) {				/* 横の数の分 			*/
			puzzles[i][j] = 'O';						/* puzzle[i][j]に'O'で初期化 		*/
			/* memset(*(puzzles+i)+j, 'O', sizeof(char*)); */
		}
	}
}

/** 
 * パズルの出力
 * @param (*width) 		横の配列
 * @param (*length) 	縦の配列
 * @param (*puzzles)	パズル
 * @return 				パズルの出力
*/
void showPuzzle(char *width, char *length, char **puzzles) {
	
	int i;
	int j;
	
	
	printf(" ");
	for (i = 0; i < WIDTH ; i++) {		/* 最初の横の線表示（a,b,c,d,e,****）	 */
		printf(" %c", width[i]);	
	}
	printf("\n");
	for (i = 0; i < LENGTH ; i++) {		/* 縦で、一番左にの縦の線表示（A,B,C,D,E,****） */
		printf("%c ",length[i]);
		
		for (j = 0; j < WIDTH ; j++) {	/* ある縦（puzzles[i]）にある横の情報すべて出力 */
		    printf("%c ", puzzles[i][j]); 
		}
		printf("\n");
	}
}

/** 
 * パズル変更の関数
 * @param (*inputs)		入力文字列
 * @param (*end)		終了判別の値
 * @param (*puzzles)	パズル 
 * @param (*width) 		横の配列
 * @param (*length) 	縦の配列
 * @return 				パズル変更の実行
*/
void puzzle(char *inputs, int *end, char **puzzles, char *width, char *length ) {
	
	int x;	/* 選択した横の位置 */
	int y;	/* 選択した縦の位置 */
	int i;
	int j;

	
	if ( !strcmp(inputs, END) ){			/* 入力が'zz'だった場合、プログラム終了 */
		(*end)++;
		
	} else {
		
		for ( i = 0 ; i < WIDTH ; i++ ) {	/* 選択した横の位置初期化 */
			if (width[i] == inputs[0]) {
				x = i;
				break;
			}
		}
		for ( i = 0 ; i < LENGTH ; i++ ) {	/* 選択した縦の位置初期化 */
			if (length[i] == inputs[1]) {
				y = i;
				break;
			}
		}
		
		for ( j = y-1 ; j <= y+1 ; j++) {				/* 横の位置の上下のエリアまで */
			for ( i = x-1 ; i <= x+1 ; i++) {			/* 縦の位置の右左のエリアまで */
				if ( i == x || j == y ) {				/* '十'の形で変更させるため（なければ■範囲で変換される） */
					if (chkover(&j, &i) == 1) {			/* 横と縦の数字がパズル内の位置であるか判断 */
						if (*(*(puzzles+j)+i) == 'O') {	/* 既に'O'なら'X'に変える */
							*(*(puzzles+j)+i) = 'X';
						} else {
							*(*(puzzles+j)+i) = 'O';	/* 既に'X'なら'O'に変える */
						}
					}
				} else { 
					continue;							/* ■範囲で'十'を除いた部分、変換せず */
				}
			}
		}
	}
}


/** 
 * パズル内の位置であるか判別
 * @param (*j) 		横の位置
 * @param (*i) 		縦の位置
 * @return 	1		パズル変更の実行
 * @return 	0		パズル変更せず 
*/
int chkover(int *j, int *i) {
	if (*i >= 0 && *i < WIDTH && *j >= 0 && *j < LENGTH) return 1;
	
	return 0;
}






/** 
 * 入力
 * @param (*inputs)		入力文字列 
 * @param (*width) 		横の配列
 * @param (*length) 	縦の配列
 * @return 				パズル変更の実行
*/
void inputNumber(char *inputs , char *width, char *length ){
	
	int i=0;				/* 入力桁の添え数 				*/
	
	printf("\n\n文字を入力してください\n>>");
	
	while (1) {								/* 入力が正しい時まで */
		inputs[i] = getchar();
		
		if (inputs[i] == '\n') {			/* 文字列の尾に着いたら */
			if (i == 0) {
				system("cls");					/* 未入力の場合、再入力に戻る */
				printf("\nエラー発生\n未入力です。入力し直してください\n>>");
				inputs[i] = '\0';			/* 最後入力値を数字に変換 */
				continue;
			} else if (i == 1) {
				system("cls");
				printf("\nエラー発生\n間違った入力です。入力し直してください\n>>");
				toEmpty(inputs);
				i = 0;
				continue;
			} else {
				inputs[i] = '\0';
				printf("入力文字 : %s\n\n", inputs);
				if (checkInput(inputs, width, length)) {	/* 横・縦のない入力の場合 */
					system("cls");
					printf("\nエラー発生\n間違った入力です。入力し直してください\n>>");
					toEmpty(inputs);
					i = 0;
					continue;
				} else {					/* 入力が正常の場合、入力終了 */
					break;
				}
			}
	   	} else if (i > SIZE-1  ) {			/* 入力範囲から外れた時、再入力を求める */
			system("cls");
			printf("\nエラー発生\n半角 数字で %d以内の数字で入力し直してください\n>>", SIZE);
			toEmpty(inputs);
			while (getchar() != '\n');
			i = 0;
			continue;
		} else {
			i++;
		}
	}
}

/** 
 * 2文字の再度チェック
 * @param (*inputs)		入力文字列 
 * @param (*width) 		横の配列
 * @param (*length) 	縦の配列
 * @return 1 			誤入力であった
 * @return 0			配列にある入力であった
*/
int checkInput(char *inputs, char *width, char *length){

	
	int i ;
	int widthPass = 0;		/* 横の正カウント */
	int lengthPass = 0;		/* 縦の正カウント */
	
	/* 両方'z'ではなかった場合 */
	if ( (inputs[0]=='z' && inputs[1]!='z') || (inputs[1]=='z' && inputs[0]!='z') ) {
		return 1;
	}
	
	if (strchr(width, inputs[0]) != NULL) {		/* 横の入力が正しかった場合 */
		widthPass++;
	}
	
	if (strchr(length, inputs[1]) != NULL) {	/* 縦の入力が正しかった場合 */
		lengthPass++;
	}
	
	if (widthPass != 0 && lengthPass != 0) {	/* 横・縦の入力が当てはまる時 */
		return 0;
	
	} else return 1;							/* 入力がどっちか間違っていた時 */
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

