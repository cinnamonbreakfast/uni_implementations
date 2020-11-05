bits 32 ; assembling for the 32 bits architecture

global start        

extern exit
import exit msvcrt.dll


; a, b, c : byte
; d, x : word
; [100-10*a+4*(b+c)]-d
segment data use32 class=data
    a db 10
    b db 5
    c db 3
    d dw 4
    x dw 0
    
    ; the result is:
    ; 28


segment code use32 class=code
    start:
        MOV AL, [a]
        MUL byte 10
        MOV DX, AX
        MOV AL, [b]
        ADD AL, [c]
        MUL 4
        ADD DX, AX
        MOV AX, 100
        SUB AX, DX
        SUB AX, [d]
        
        MOV [x], AX
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
