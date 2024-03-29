/**
 * @file 4.c
 * @brief	２００９年度と２０１０年度の比較を行った結果を出力するプログラムを作成せよ。
 * @author Lee Hyeongbeen
 * @date 2024/2/29
*/


#include <stdio.h>
#include <string.h>
#include <locale.h>
#include <wchar.h>
#include <stdlib.h>
#include <wctype.h>
#include <ctype.h>

#define NAME_SIZE 10		/* 名前の長さ制限 */
#define SIZE2009 6			/* 2009年度の売上数 */
#define SIZE2010 5			/* 2010年度の売上数 */

/* 関数宣言 */
int nameAsc(const void* first, const void* second);


/* 売上の件 */
typedef struct Member {
	
	char name[NAME_SIZE];
	int score;
	
} member;

/* 比較結果の件 */
typedef struct Merge {
	
	char name[NAME_SIZE];
	char score[NAME_SIZE];
	
} merge;


/* メイン */
void main (){
	
	int i = 0;
	int j = 0;
	int noName = 0;		/* 名前がなかった場合の添え数 */
	int mergeLen = 0;	/* mergeの実際配列数 */
	
	merge merges[SIZE2009 + SIZE2010];
	
	member mem2009[SIZE2009] = { {"ｶｽｶﾞ", 325}, {"ﾔﾏｼﾀ", 277}, {"ﾀﾅｶ", 333}, {"ﾖｼｵｶ", 192}, {"ﾀｹﾓﾄ", 412}, {"ｱﾄｳ", 270}};
	member mem2010[SIZE2010] = { {"ｽｽﾞｷ", 210}, {"ﾔﾏｼﾀ", 233}, {"ﾀｹﾓﾄ", 445}, {"ﾖｼﾀﾞ", 145}, {"ｶｽｶﾞ", 357} };
	
	
	/* if (mem2009[i].name[0] == '\0') { 
        continue; 
    }*/		
	
	
	while (i < SIZE2009) {								/* 2009配列のある分、1重ループ */
	    if ( strcmp( mem2009[i].name, mem2010[j].name) == 0) {		/* 名前が一致した場合 */
	        strcpy( merges[mergeLen].name, mem2009[i].name);		/* 名前をmergesに保存 */
	        sprintf (merges[mergeLen].score, "%d", mem2010[j].score - mem2009[i].score);	/* 売上をmergesに保存 */
	        mergeLen++;												/* mergeLen増加 		*/
	        j = noName = 0;											/* jとnoNameの初期化	*/
			i++;													/* 2009の次に進む */
	    
		} else {													/* 不一致した場合 */
	        noName++;												/* noNameの増加 */
			
	        if ( noName == SIZE2010 ) {								/* 2010にない名前だった場合 */
	            strcpy( merges[mergeLen].name, mem2009[i].name);	/* 名前をmergesに保存	*/
	            strcpy( merges[mergeLen].score, "退社");			/* 退社とmergesに保存 	*/
	            mergeLen++;											/* mergeLen増加 		*/
		        j = noName = 0;										/* jとnoNameの初期化	*/
				i++;												/* 2009の次に進む		*/
	        } else {
				j++;												/* 2010の次に進む		*/
	        }
		}
	}
	
	for (i = 0 ; i < SIZE2010 ; i++) {								/* 入社の判定 */
		if (mem2010[i].name[0] == '\0') { 
	        continue; 
	    }		
	    for (j = 0; j < SIZE2009; j++) {							
			if ( strcmp( mem2010[i].name, mem2009[j].name) != 0) {	/* 名前が不一致した場合 */
				noName++;
	        } 
	    }
		if ( noName == SIZE2009 ) {									/* 名前が2009にいない場合 */
			strcpy( merges[mergeLen].name, mem2010[i].name );		/* 名前をmergesに保存	*/
			strcpy( merges[mergeLen].score, "入社" );				/* 入社とmergesに保存 	*/
			mergeLen++;												/* mergeLen増加 		*/
		}
		noName = 0;													/* 一致した名前があった場合、次の確認に進む */
	}
	
	qsort( merges , mergeLen, sizeof(merge), nameAsc);				/* 名前の昇順の実行 */
	
	printf("\n\n【売り上げ差額】\n");
	for ( i = 0 ; i < mergeLen ; i++ ) {							/* 結果出力 */
		printf("%s\t", merges[i].name);
		printf("%s\n", merges[i].score);
	}
	
	getchar();
	getchar();
	
	
}

/**
 * 名前の昇順関数(qsort)
 * @return  	ソートの実行
*/
int nameAsc(const void* first, const void* second) {
    const merge* a = (const merge*)first;
    const merge* b = (const merge*)second;
    return strcmp(a->name, b->name);
}



/*
	for (i = 0 ; i < SIZE2009 ; i++) {

	    for (j = 0 ; j < SIZE2010 ; j++) {
			if ( strcmp( mem2009[i].name, mem2010[j].name) == 0) {
				strcpy( merges[symbol].name, mem2009[i].name );
				sprintf( merges[symbol].score, "%d", mem2010[j].score - mem2009[i].score );
				symbol++;
				break;
	        } else {
				noName++;
				if ( noName == SIZE2010 ) {
					strcpy( merges[symbol].name, mem2009[i].name );
					strcpy( merges[symbol].score, "�ﾜﾞ�" );
					symbol++;
				}
			}
	    }
		noName = 0;
	}

*/