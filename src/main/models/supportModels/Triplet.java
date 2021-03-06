package main.models.supportModels;

import main.myException.TripletBuildException;

public class Triplet<T> {

    private  T first;
    private  T second;
    private  T last;

    private Triplet(Builder<T> builder) {
        this.first = builder.first;
        this.second = builder.second;
        this.last = builder.last;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public T getLast() {
        return last;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public void setLast(T last) {
        this.last = last;
    }

    public static class Builder<T>{
        private T first;
        private T second;
        private T last;

        public Triplet<T> build() throws TripletBuildException {
            if (first != null && second != null && last != null)
                return new Triplet<>(this);
            else {
                throw new TripletBuildException();
            }
        }

        public Builder<T> setFirst(T first) {
            this.first = first;
            return this;
        }

        public Builder<T> setSecond(T second) {
            this.second = second;
            return this;
        }

        public Builder<T> setLast(T last) {
            this.last = last;
            return this;
        }
    }
}
