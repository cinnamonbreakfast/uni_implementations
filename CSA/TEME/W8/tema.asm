bits 32

global start        

extern exit
import exit msvcrt.dll

;Given an array A of words, build two arrays of bytes:  
; - array B1 contains as elements the higher part of the words from A
; - array B2 contains as elements the lower part of the words from A

segment data use32 class=data
    A dw 0xB1, 0x23, 0x90
    
    lenA EQU ($-A)
    
    B1 times lenA db 0
    B2 times lenA db 0

segment code use32 class=code
    start:
        MOV ECX, lenA
        JECXZ final
        
        MOV ESI, A
        MOV EDI, B2
        
        CLD

        sir1:
            LODSB
            
            AND AX, 0001
            
            STOSB
        LOOP sir1
        
        MOV ECX, lenA
        JECXZ final
        
        MOV ESI, A
        MOV EDI, B1
        
        CLD
        
        sir2:
            LODSB
            
            SHR EAX, 14
            
            AND AX, 0001
            STOSB
            
        LOOP sir2
        
        
        final:
        push    dword 0
        call    [exit]
