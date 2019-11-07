package com.faramarz.tictacdev.mycustomcounter;

import android.os.Handler;

import android.view.View;
import android.view.MotionEvent;

public class CounterHandler {
    //  https://gist.github.com/nomanr/d142f4ccaf55ceba22e7f7122b55b9b6

    final Handler handler = new Handler();
    private View incrementalView;
    private View decrementalView;
    private long minRange = -1;
    private long maxRange = -1;
    private long startNumber = 0;
    private long counterStep = 1;
    private int counterDelay = 50; //millis

    private boolean isCycle = false;
    private boolean autoIncrement = false;
    private boolean autoDecrement = false;

    private CounterListener listener;


    private Runnable counterRunnable = new Runnable() {
        @Override
        public void run() {
            if (autoIncrement) {
                increment();
                handler.postDelayed(this, counterDelay);
            } else if (autoDecrement) {
                decrement();
                handler.postDelayed(this, counterDelay);
            }
        }
    };

    private CounterHandler(Builder builder) {
        incrementalView = builder.incrementalView;
        decrementalView = builder.decrementalView;
        minRange = builder.minRange;
        maxRange = builder.maxRange;
        startNumber = builder.startNumber;
        counterStep = builder.counterStep;
        counterDelay = builder.counterDelay;
        isCycle = builder.isCycle;
        listener = builder.listener;

        initDecrementalView();
        initIncrementalView();

        if (listener != null) {
            listener.onIncrement(incrementalView, startNumber);
            listener.onDecrement(decrementalView, startNumber);
        }
    }


    private void initIncrementalView() {
        incrementalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });

        incrementalView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                autoIncrement = true;
                handler.postDelayed(counterRunnable, counterDelay);
                return false;
            }
        });
        incrementalView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP && autoIncrement) {
                    autoIncrement = false;
                }
                return false;
            }
        });

    }

    private void initDecrementalView() {
        decrementalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        });

        decrementalView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                autoDecrement = true;
                handler.postDelayed(counterRunnable, counterDelay);
                return false;
            }
        });
        decrementalView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP && autoDecrement) {
                    autoDecrement = false;
                }
                return false;
            }
        });

    }

    private void increment() {
        long number = startNumber;

        if (maxRange != -1) {
            if (number + counterStep <= maxRange) {
                number += counterStep;
            } else if (isCycle) {
                number = minRange == -1 ? 0 : minRange;
            }
        } else {
            number += counterStep;
        }

        if (number != startNumber && listener != null) {
            startNumber = number;
            listener.onIncrement(incrementalView, startNumber);
        }

    }

    private void decrement() {
        long number = startNumber;

        if (minRange != -1) {
            if (number - counterStep >= minRange) {
                number -= counterStep;
            } else if (isCycle) {
                number = maxRange == -1 ? 0 : maxRange;

            }
        } else {
            number -= counterStep;
        }

        if (number != startNumber && listener != null) {
            startNumber = number;
            listener.onDecrement(decrementalView, startNumber);
        }
    }


    public interface CounterListener {
        void onIncrement(View view, long number);

        void onDecrement(View view, long number);

    }


    public static final class Builder {
        private View incrementalView;
        private View decrementalView;
        private long minRange = -1;
        private long maxRange = -1;
        private long startNumber = 0;
        private long counterStep = 1;
        private int counterDelay = 50;
        private boolean isCycle;
        private CounterListener listener;

        public Builder() {
        }

        public Builder incrementalView(View val) {
            incrementalView = val;
            return this;
        }

        public Builder decrementalView(View val) {
            decrementalView = val;
            return this;
        }

        public Builder minRange(long val) {
            minRange = val;
            return this;
        }

        public Builder maxRange(long val) {
            maxRange = val;
            return this;
        }

        public Builder startNumber(long val) {
            startNumber = val;
            return this;
        }

        public Builder counterStep(long val) {
            counterStep = val;
            return this;
        }

        public Builder counterDelay(int val) {
            counterDelay = val;
            return this;
        }

        public Builder isCycle(boolean val) {
            isCycle = val;
            return this;
        }

        public Builder listener(CounterListener val) {
            listener = val;
            return this;
        }

        public CounterHandler build() {
            return new CounterHandler(this);
        }
    }
}