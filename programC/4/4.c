/**
 * @file 4.c
 * @brief	‚Q‚O‚O‚X”N“x‚Æ‚Q‚O‚P‚O”N“x‚Ì”äŠr‚ğs‚Á‚½Œ‹‰Ê‚ğo—Í‚·‚éƒvƒƒOƒ‰ƒ€‚ğì¬‚¹‚æB
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

#define NAME_SIZE 10		/* –¼‘O‚Ì’·‚³§ŒÀ */
#define SIZE2009 6			/* 2009”N“x‚Ì”„ã” */
#define SIZE2010 5			/* 2010”N“x‚Ì”„ã” */

/* ŠÖ”éŒ¾ */
int nameAsc(const void* first, const void* second);


/* ”„ã‚ÌŒ */
typedef struct Member {
	
	char name[NAME_SIZE];
	int score;
	
} member;

/* ”äŠrŒ‹‰Ê‚ÌŒ */
typedef struct Merge {
	
	char name[NAME_SIZE];
	char score[NAME_SIZE];
	
} merge;


/* ƒƒCƒ“ */
void main (){
	
	int i = 0;
	int j = 0;
	int noName = 0;		/* –¼‘O‚ª‚È‚©‚Á‚½ê‡‚Ì“Y‚¦” */
	int mergeLen = 0;	/* merge‚ÌÀÛ”z—ñ” */
	
	merge merges[SIZE2009 + SIZE2010];
	
	member mem2009[SIZE2009] = { {"¶½¶Ş", 325}, {"ÔÏ¼À", 277}, {"ÀÅ¶", 333}, {"Ö¼µ¶", 192}, {"À¹ÓÄ", 412}, {"±Ä³", 270}};
	member mem2010[SIZE2010] = { {"½½Ş·", 210}, {"ÔÏ¼À", 233}, {"À¹ÓÄ", 445}, {"Ö¼ÀŞ", 145}, {"¶½¶Ş", 357} };
	
	
	/* if (mem2009[i].name[0] == '\0') { 
        continue; 
    }*/		
	
	
	while (i < SIZE2009) {								/* 2009”z—ñ‚Ì‚ ‚é•ªA1dƒ‹[ƒv */
	    if ( strcmp( mem2009[i].name, mem2010[j].name) == 0) {		/* –¼‘O‚ªˆê’v‚µ‚½ê‡ */
	        strcpy( merges[mergeLen].name, mem2009[i].name);		/* –¼‘O‚ğmerges‚É•Û‘¶ */
	        sprintf (merges[mergeLen].score, "%d", mem2010[j].score - mem2009[i].score);	/* ”„ã‚ğmerges‚É•Û‘¶ */
	        mergeLen++;												/* mergeLen‘‰Á 		*/
	        j = noName = 0;											/* j‚ÆnoName‚Ì‰Šú‰»	*/
			i++;													/* 2009‚ÌŸ‚Éi‚Ş */
	    
		} else {													/* •sˆê’v‚µ‚½ê‡ */
	        noName++;												/* noName‚Ì‘‰Á */
			
	        if ( noName == SIZE2010 ) {								/* 2010‚É‚È‚¢–¼‘O‚¾‚Á‚½ê‡ */
	            strcpy( merges[mergeLen].name, mem2009[i].name);	/* –¼‘O‚ğmerges‚É•Û‘¶	*/
	            strcpy( merges[mergeLen].score, "‘ŞĞ");			/* ‘ŞĞ‚Æmerges‚É•Û‘¶ 	*/
	            mergeLen++;											/* mergeLen‘‰Á 		*/
		        j = noName = 0;										/* j‚ÆnoName‚Ì‰Šú‰»	*/
				i++;												/* 2009‚ÌŸ‚Éi‚Ş		*/
	        } else {
				j++;												/* 2010‚ÌŸ‚Éi‚Ş		*/
	        }
		}
	}
	
	for (i = 0 ; i < SIZE2010 ; i++) {								/* “üĞ‚Ì”»’è */
		if (mem2010[i].name[0] == '\0') { 
	        continue; 
	    }		
	    for (j = 0; j < SIZE2009; j++) {							
			if ( strcmp( mem2010[i].name, mem2009[j].name) != 0) {	/* –¼‘O‚ª•sˆê’v‚µ‚½ê‡ */
				noName++;
	        } 
	    }
		if ( noName == SIZE2009 ) {									/* –¼‘O‚ª2009‚É‚¢‚È‚¢ê‡ */
			strcpy( merges[mergeLen].name, mem2010[i].name );		/* –¼‘O‚ğmerges‚É•Û‘¶	*/
			strcpy( merges[mergeLen].score, "“üĞ" );				/* “üĞ‚Æmerges‚É•Û‘¶ 	*/
			mergeLen++;												/* mergeLen‘‰Á 		*/
		}
		noName = 0;													/* ˆê’v‚µ‚½–¼‘O‚ª‚ ‚Á‚½ê‡AŸ‚ÌŠm”F‚Éi‚Ş */
	}
	
	qsort( merges , mergeLen, sizeof(merge), nameAsc);				/* –¼‘O‚Ì¸‡‚ÌÀs */
	
	printf("\n\ny”„‚èã‚°·Šzz\n");
	for ( i = 0 ; i < mergeLen ; i++ ) {							/* Œ‹‰Êo—Í */
		printf("%s\t", merges[i].name);
		printf("%s\n", merges[i].score);
	}
	
	getchar();
	getchar();
	
	
}

/**
 * –¼‘O‚Ì¸‡ŠÖ”(qsort)
 * @return  	ƒ\[ƒg‚ÌÀs
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
					strcpy( merges[symbol].score, "÷ÜŞä" );
					symbol++;
				}
			}
	    }
		noName = 0;
	}

*/