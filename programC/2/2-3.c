/**
 * @file 2-3.c
 * @brief	‚P`‚P‚O‚O‚O‚ÌŠÔ‚É‚ ‚é‘f”‚ğ‚·‚×‚Ä•\¦‚·‚éƒvƒƒOƒ‰ƒ€‚ğì¬‚¹‚æB
			‚PA‚Q‚É‚Æ‚ç‚í‚ê‚¸‘‚¢ƒAƒ‹ƒSƒŠƒYƒ€‚ğì¬‚¹‚æB
 * @author Lee Hyeongbeen
 * @date 2024/2/27
*/

#include <stdio.h>
#include <ctype.h>
#include <string.h.>
#include <stdlib.h>

#define TIMES 2			/* “ü—Í‰ñ”‚Ìİ’è 	*/
#define SIZE 4			/* “ü—ÍŒ…‚Ì§ŒÀ		*/
#define NUM1 1			/* y‚P`‚P‚O‚O‚O‚ÌŠÔz‚Ì1 		*/
#define NUM2 1000		/* y‚P`‚P‚O‚O‚O‚ÌŠÔz‚Ì1000 	*/



/* ŠÖ”éŒ¾ */
void prime(void) {
	
	int i;					/* “Y‚¦” */
	int count = 0;			/* ‘f””»’f‚ÌƒJƒEƒ“ƒg 	*/
	int num1 = NUM1;		/* 1‚Ì”š‚Ì‰Šú‰» 		*/
	int num2 = NUM2;		/* 1000‚Ì”š‚Ì‰Šú‰» 	*/
	
	if (num1 > num2) {		/* ‰º‚ÌƒR[ƒfƒBƒ“ƒO‚½‚ßA•K‚¸num2‚ªnum1‚æ‚è‘å‚«‚­‚³‚¹‚é‚×‚«	*/
		i = num1;
		num1 = num2;
		num2 = i;
	}
	printf("\n-----------%d‚Æ%d‚ÌŠÔ‚Ì‘f”o—Í----------------\n\n",NUM1,NUM2);
	
	for ( ; num1 < num2 ; num1++) {			/* num1‚Ænum2‚ÌŠÔ‚Ü‚ÅAnum1+1‚©‚çŠJn 		*/
		for (i = 2 ; i < num1 ; i++) {		/* i‚ğ2‚©‚çnum1‘O‚Ü‚ÅL‚Î‚µ‘±‚¯‚é			*/
			if (num1 % i == 0) {			/* num1‚ª‘f”‚Å‚ ‚Á‚½ê‡Ai‚Æ‚Ì”äŠrI—¹	*/
				break;
			}
		}
		if (num1 == i) {					/* ‘f”‚¾‚½”»’f‚³‚êAfor•¶‚©‚ç“¦‚ê‚½ê‡	*/
			printf ("%d\t",num1);			/* o—Í					*/
			if (++count % 6 == 0) {			/* o—Í6ŒÂ’PˆÊ‚Å‰üs	*/
				printf("\n");
			}
		}
	}

	if (count) {							/* ‘f”‚ª‚ ‚Á‚½ê‡A”‚ğo—Í */
		printf("\n\nyŒ‹‰Êz%dŒÂ‚Ì‘f”‚ª‚ ‚è‚Ü‚·B", count);	
			
	} else {								/* 2‚Â‚Ì”š‚ÌŠÔ‚É‘f”‚ª‚È‚©‚Á‚½ê‡ */
		printf("\n\nyŒ‹‰Êz‘f”‚ª‚ ‚è‚Ü‚¹‚ñ‚Å‚µ‚½B");		
	}
	printf("\n-------------------------------------------------\n");		
}


/* ƒƒCƒ“ */
int main(void){	
	
	
	prime();		/* ‘f”Šm”F			*/
	
	
	printf("\n\nyI—¹z\n");
	
	getchar();
	return 0;
}
