package com.example.library.web;

import com.example.library.model.Book;
import com.example.library.model.Loan;
import com.example.library.model.Member;
import com.example.library.service.BookService;
import com.example.library.service.LoanService;
import com.example.library.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PageController {

    private final BookService bookService;
    private final MemberService memberService;
    private final LoanService loanService;


    public PageController(BookService bookService, MemberService memberService, LoanService loanService) {
        this.bookService = bookService;
        this.memberService = memberService;
        this.loanService = loanService;
    }

    @GetMapping("/books-page")
    public String showBooksPage(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book"; // templates/add-book.html
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/books-page";
    }

    @GetMapping("/edit-book/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id).orElseThrow(() -> new IllegalArgumentException("Érvénytelen könyv ID: " + id));
        model.addAttribute("book", book);
        return "edit-book";
    }

    @PostMapping("/edit-book/{id}")
    public String editBook(@PathVariable Long id, @ModelAttribute Book book) {
        book.setId(id);
        bookService.addBook(book); // save() újként vagy módosítottként is működik
        return "redirect:/books-page";
    }

    @GetMapping("/delete-book/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books-page";
    }

    @GetMapping("/members-page")
    public String showMembersPage(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "members";
    }

    @GetMapping("/add-member")
    public String showAddMemberForm(Model model) {
        model.addAttribute("member", new Member());
        return "add-member";
    }

    @PostMapping("/add-member")
    public String addMember(@ModelAttribute Member member) {
        memberService.addMember(member);
        return "redirect:/members-page";
    }

    @GetMapping("/edit-member/{id}")
    public String showEditMemberForm(@PathVariable Long id, Model model) {
        Member member = memberService.getMemberById(id)
                .orElseThrow(() -> new IllegalArgumentException("Érvénytelen tag ID: " + id));
        model.addAttribute("member", member);
        return "edit-member";
    }

    @PostMapping("/edit-member/{id}")
    public String editMember(@PathVariable Long id, @ModelAttribute Member member) {
        member.setId(id);
        memberService.addMember(member);
        return "redirect:/members-page";
    }

    @GetMapping("/delete-member/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/members-page";
    }

    @GetMapping("/loans-page")
    public String showLoansPage(Model model) {
        model.addAttribute("loans", loanService.getAllLoans());
        return "loans";
    }

    @GetMapping("/add-loan")
    public String showAddLoanForm(Model model) {
        model.addAttribute("loan", new Loan());
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("books", bookService.getAllBooks());
        return "add-loan";
    }

    @PostMapping("/add-loan")
    public String addLoan(@ModelAttribute Loan loan) {
        loanService.addLoan(loan);
        return "redirect:/loans-page";
    }

    @GetMapping("/edit-loan/{id}")
    public String showEditLoanForm(@PathVariable Long id, Model model) {
        Loan loan = loanService.getLoanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Érvénytelen kölcsönzés ID: " + id));
        model.addAttribute("loan", loan);
        model.addAttribute("members", memberService.getAllMembers());
        model.addAttribute("books", bookService.getAllBooks());
        return "edit-loan";
    }

    @PostMapping("/edit-loan/{id}")
    public String editLoan(@PathVariable Long id, @ModelAttribute Loan loan) {
        loan.setId(id);
        loanService.addLoan(loan);
        return "redirect:/loans-page";
    }

    @GetMapping("/delete-loan/{id}")
    public String deleteLoan(@PathVariable Long id) {
        loanService.deleteLoan(id);
        return "redirect:/loans-page";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
