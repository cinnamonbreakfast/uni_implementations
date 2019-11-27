bits 32

global start        

extern exit
import exit msvcrt.dls

segment data use32 class=data
    a db 'abcd'
    LEN EQU ($-a)
    b TIMES LEN DB 0
    

segment code use32 class=code
    start:
        MOV EAX, 0
        
        bucla:
            MOV AL, [a + ECX]
            SUB AL, 'a' - 'A'
            MOV [b + ECX], AL
            
            INC ECX
            CMP ECX, LEN
            JB bucla
        
        
        push    dword 0
        call    [exit]
