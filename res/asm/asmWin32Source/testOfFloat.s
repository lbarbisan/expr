	.file	"testOfFloat.c"
	.def	___main;	.scl	2;	.type	32;	.endef
	.text
.globl _main
	.def	_main;	.scl	2;	.type	32;	.endef
_main:
	pushl	%ebp
	movl	%esp, %ebp
	subl	$24, %esp
	andl	$-16, %esp
	movl	$0, %eax
	addl	$15, %eax
	addl	$15, %eax
	shrl	$4, %eax
	sall	$4, %eax
	movl	%eax, -16(%ebp)
	movl	-16(%ebp), %eax
	call	__alloca
	call	___main
	movl	$0xc00ccccd, %eax
	movl	%eax, -4(%ebp)
	movl	$0x40533333, %eax
	movl	%eax, -8(%ebp)
	flds	-4(%ebp)
	fadds	-8(%ebp)
	fstps	-12(%ebp)
	leave
	ret
