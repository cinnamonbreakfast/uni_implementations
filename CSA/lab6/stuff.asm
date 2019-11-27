bits 32

global start        

extern exit
import exit msvcrt.dll

; array of words
; if byte low < byte high -> b[i] = 1
; if byte low = byte high -> b[i] = 0
; if byte low > byte high -> b[i] = -1

segment data use32 class=data
    a dw 0x1234, 0xAB12, 0x1111
    len EQU ($-a)/2
    b times len db 2

segment code use32 class=code
    start:
        MOV ECX, len
        JECXZ final
        
        MOV ESI, a
        MOV EDI, b
        
        bucla:
            LODSW ; AX = a[i]
            CMP AL, AH
            JL caz1
            JE caz2
                ;caz3
                MOV AL, -1
                JMP continue
            caz1:
                MOV AL, 1
                JMP continue
            caz2:
                MOV AL, 1
                
            continue:
            STOSB
        LOOP bucla
        
        final:
        ; thats all folks!
        push    dword 0
        call    [exit]
