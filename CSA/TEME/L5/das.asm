bits 32

global start        

extern exit
import exit msvcrt.dll

;Given an array A of words, build two arrays of bytes:  
;   -   array B1 contains as elements the higher part of the words from A
;   -   array B2 contains as elements the lower part of the words from A

segment data use32 class=data
    A dw 0x1234, 0xAB12, 0x1111
    len EQU ($-A)
    B1 times len db 0
    B2 times len db 0
    

segment code use32 class=code
    start:
        MOV ECX, len
        JECXZ skip1
        
        MOV ESI, A
        MOV EDI, B1

        bucla:
            LODSW ; AX = a[i]
            ;CMP AL, AH
            STOSB

        LOOP bucla

        final:
        push    dword 0
        call    [exit]
