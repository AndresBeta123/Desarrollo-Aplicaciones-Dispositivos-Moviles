package co.edu.unal.tictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class BoardView extends View {
    public static final int GRID_WIDTH = 6;
    private Bitmap mHumanBitmap;
    private Bitmap mComputerBitmap;
    private Paint mPaint;
    private TicTacToeGame mGame;

    public void setGame(TicTacToeGame game) {
        mGame = game;
    }
    public int getBoardCellWidth() {
        return getWidth() / 3;
    }
    public int getBoardCellHeight() {
        return getHeight() / 3;
    }
    public void initialize() {
        mHumanBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.x_img);
        mComputerBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.o_img);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }
    public BoardView(Context context) {
        super(context);
        initialize();
    }
    public BoardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }
    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    // Determine the width and height of the View
        int boardWidth = getWidth();
        int boardHeight = getHeight();
    // Make thick, light gray lines
        mPaint.setColor(Color.LTGRAY);
        mPaint.setStrokeWidth(GRID_WIDTH);
    // Draw the two vertical board lines
        int cellWidth = boardWidth / 3;
        canvas.drawLine(cellWidth, 0, cellWidth, boardHeight, mPaint);
        canvas.drawLine(cellWidth * 2, 0, cellWidth * 2, boardHeight, mPaint);
        int cellHeight = boardHeight / 3;
        canvas.drawLine(0, cellHeight, boardWidth, cellHeight, mPaint);
        canvas.drawLine(0, cellHeight * 2, boardWidth, cellHeight * 2, mPaint);
        // Draw all the X and O images
        for (int i = 0; i < 9; i++) {
            int col = i % 3;
            int row = i / 3;
        // Define the boundaries of a destination rectangle for the image
            int left = col * cellWidth;
            int top = row * cellHeight;
            int right = (col + 1) * cellWidth;
            int bottom = (row + 1) * cellHeight;

            if (mGame != null && mGame.getBoardOccupation(i) == TicTacToeGame.HUMAN_PLAYER) {
                canvas.drawBitmap(mHumanBitmap,
                        null, // src
                        new Rect(left, top, right, bottom), // dest
                        null);

            }
            else if (mGame != null && mGame.getBoardOccupation(i) == TicTacToeGame.COMPUTER_PLAYER) {
                canvas.drawBitmap(mComputerBitmap,
                        null, // src
                        new Rect(left, top, right, bottom), // dest
                        null);

            }
        }
    }
}
