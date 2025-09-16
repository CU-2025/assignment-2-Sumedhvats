class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
    
    public BookNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}

class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(message);
    }
    
    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}

class Library {
    public void borrowBook(String bookTitle, int availableCopies, int userId, String userRole) 
            throws BookNotAvailableException {
        
        if (bookTitle == null || bookTitle.trim().isEmpty()) {
            throw new NullPointerException("Book title cannot be null or empty");
        }
        
        if (userId < 0) {
            throw new InvalidUserException("User ID cannot be negative. Provided ID: " + userId);
        }
        
        if (userRole == null || (!userRole.equals("student") && !userRole.equals("faculty"))) {
            throw new InvalidUserException("Invalid user role. Must be 'student' or 'faculty'. Provided: " + userRole);
        }
        
        if (availableCopies < 0) {
            throw new IllegalArgumentException("Available copies cannot be negative");
        }
        
        if (availableCopies == 0) {
            throw new BookNotAvailableException("Book '" + bookTitle + "' is not available for borrowing. No copies left.");
        }
        
        System.out.println("Book '" + bookTitle + "' successfully borrowed by " + userRole + " (ID: " + userId + ")");
    }
    
    public void returnBook(String bookTitle, int userId) {
        if (bookTitle == null || bookTitle.trim().isEmpty()) {
            throw new NullPointerException("Book title cannot be null or empty");
        }
        
        if (userId < 0) {
            throw new InvalidUserException("User ID cannot be negative. Provided ID: " + userId);
        }
        
        System.out.println("Book '" + bookTitle + "' successfully returned by user (ID: " + userId + ")");
    }
}

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        
        System.out.println("=== Library Management System Testing ===\n");
        
        System.out.println("1. Testing valid book borrowing:");
        try {
            library.borrowBook("Java Programming", 5, 101, "student");
        } catch (BookNotAvailableException | InvalidUserException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n2. Testing borrowing with no available copies:");
        try {
            library.borrowBook("Advanced Algorithms", 0, 102, "faculty");
        } catch (BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidUserException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n3. Testing borrowing with invalid user ID:");
        try {
            library.borrowBook("Data Structures", 3, -5, "student");
        } catch (BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidUserException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n4. Testing borrowing with invalid user role:");
        try {
            library.borrowBook("Operating Systems", 2, 103, "admin");
        } catch (BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidUserException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n5. Testing valid book return:");
        try {
            library.returnBook("Java Programming", 101);
        } catch (InvalidUserException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n6. Testing return with null book title:");
        try {
            library.returnBook(null, 104);
        } catch (InvalidUserException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n7. Testing return with empty book title:");
        try {
            library.returnBook("", 105);
        } catch (InvalidUserException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n8. Testing return with negative user ID:");
        try {
            library.returnBook("Database Systems", -10);
        } catch (InvalidUserException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n9. Testing borrowing with null book title:");
        try {
            library.borrowBook(null, 2, 106, "faculty");
        } catch (BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidUserException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n10. Testing borrowing with null user role:");
        try {
            library.borrowBook("Network Security", 1, 107, null);
        } catch (BookNotAvailableException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidUserException | NullPointerException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        System.out.println("\n=== Testing Complete ===");
    }
}