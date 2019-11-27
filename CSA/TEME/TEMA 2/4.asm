bits 32 ; assembling for the 32 bits architecture

global start        

extern exit
import exit msvcrt.dll


; a, b, c, d, x : word
; (a-b+c)-(d+d)
segment data use32 class=data
    a dw 10
    b dw 5
    c dw 3
    d dw 4
    x db 0


segment code use32 class=code
    start:
        MOV AX, [a]
        SUB AX, [b]
        ADD AX, [c]
        
        MOV BX, [d]
        ADD BX, [d]
        
        SUB AX, BX
        MOV [x], AX
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
