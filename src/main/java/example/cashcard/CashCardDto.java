package example.cashcard;

public record CashCardDto(Long id, Double amount, UserDto owner) {
    @Override
    public String toString() {
        return owner.id();
    }
}
