package jihanislam007.firebasesignuplogindb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameSignInEditText,
            passwordSignInEditText;
    Button logInSignInButton,
            GoSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameSignInEditText = findViewById(R.id.usernameSignInEditText);
        passwordSignInEditText = findViewById(R.id.passwordSignInEditText);

        logInSignInButton = findViewById(R.id.logInSignInButton);
        GoSignInButton = findViewById(R.id.GoSignInButton);

        logInSignInButton.setOnClickListener(this);
        GoSignInButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.logInSignInButton:

                break;

            case R.id.GoSignInButton:
                Intent intent = new Intent(this,SignUpActivity.class);
                startActivity(intent);
                break;
        }

    }
}
