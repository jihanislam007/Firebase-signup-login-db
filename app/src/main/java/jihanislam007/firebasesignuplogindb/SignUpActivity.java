package jihanislam007.firebasesignuplogindb;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    EditText usernameSignUpEditText,
            passwordSignUpEditText,
            confirmPasswordSignUpEditText;
    Button SignUpButton,
            AlreadyRegisteredButton;
    ProgressBar progressbar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameSignUpEditText = findViewById(R.id.usernameSignUpEditText);
        passwordSignUpEditText = findViewById(R.id.passwordSignUpEditText);
        confirmPasswordSignUpEditText = findViewById(R.id.confirmPasswordSignUpEditText);

        SignUpButton = findViewById(R.id.SignUpButton);
        AlreadyRegisteredButton = findViewById(R.id.AlreadyRegisteredButton);

        progressbar = findViewById(R.id.progressbar);

        SignUpButton.setOnClickListener(this);
        AlreadyRegisteredButton.setOnClickListener(this);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.SignUpButton:
                
                userRegister();
                
                break;

            case R.id.AlreadyRegisteredButton:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                break;

        }

    }

    private void userRegister() {

        String email = usernameSignUpEditText.getText().toString().trim();
        String pass = passwordSignUpEditText.getText().toString().trim();
        String con_pass = confirmPasswordSignUpEditText.getText().toString().trim();

        //checking the validity of the email
        if(email.isEmpty())
        {
            usernameSignUpEditText.setError("Enter an email address");
            usernameSignUpEditText.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            usernameSignUpEditText.setError("Enter a valid email address");
            usernameSignUpEditText.requestFocus();
            return;
        }

        //checking the validity of the password
        if(pass.isEmpty())
        {
            passwordSignUpEditText.setError("Enter a password");
            passwordSignUpEditText.requestFocus();
            return;
        }

        if(con_pass.isEmpty())
        {
            confirmPasswordSignUpEditText.setError("Enter confirm password");
            confirmPasswordSignUpEditText.requestFocus();
            return;
        }

        if(con_pass != null && !con_pass.equals(pass)){
            confirmPasswordSignUpEditText.setError("Confirm password is not match");
            confirmPasswordSignUpEditText.requestFocus();
            return;
        }

        //progressbar//
        progressbar.setVisibility(View.VISIBLE);
        //--progressbar--//

        //firebase //
        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                //progressbar//
                progressbar.setVisibility(View.GONE);
                //--progressbar--//

                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information

                    Toast.makeText(SignUpActivity.this, "Register is successful", Toast.LENGTH_SHORT).show();

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(SignUpActivity.this, "Who the hell are you", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}
