bits 32

global start        

extern exit
import exit msvcrt.dll

; Obtain the string D by concatenating the elements of S2 in reverse order and the elements found on even positions in S1. 
;   S1: '+', '2', '2', 'b', '8', '6', 'X', '8'
;   S2: 'a', '4', '5'
;   D: '5', '4', 'a', '2','b', '6', '8'

segment data use32 class=data
    S1 db '+22b86X8'
    len1 EQU ($-S1)/2
    
    S2 db 'a45'
    
    len2 EQU ($-S2)
    D times (len1+len2) db 0

segment code use32 class=code
    start:
        MOV ECX, len2
        JECXZ skip1
        
        MOV ESI, S2
        MOV EDI, D
        ADD ESI, 2
        ADD EDI, 2
        
        STD

        sir2:
            LODSB
            STOSB
        LOOP sir2
        
        skip1:
        
        MOV ECX, len1
        JECXZ final
        
        MOV ESI, S1
        MOV EDI, D
        ADD EDI, len2
        
        CLD
        
        sir1:
            INC ESI
            
            LODSB
            
            STOSB
        LOOP sir1
        
        final:
        push    dword 0
        call    [exit]
