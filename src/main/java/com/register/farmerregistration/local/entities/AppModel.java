package com.register.farmerregistration.local.entities;


import com.register.farmerregistration.local.AfterLogin;
import com.register.farmerregistration.util.UserSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.WhitespaceTokenizerFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Set;

@Slf4j
@MappedSuperclass
@AnalyzerDef(name = "customanalyzer",
        tokenizer = @TokenizerDef(factory = WhitespaceTokenizerFactory.class),
        //tokenizer = @TokenizerDef(factory = LowerCaseTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = NGramFilterFactory.class, params = {@Parameter(name = "maxGramSize", value = "1024")}),

        })
@AnalyzerDef(name = "keywordanalyzer",
        tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class))
public abstract class AppModel {

    private transient String error;
    public transient boolean isError;
    @Column(name = "CREATED_AT")
    private String createdAt;
    @Column(name = "UPDATED_AT")
    private String updatedAt;
    //private String removedAt;

    enum flagEnum {
        Active,
        Inactive
    }


    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @PrePersist
    protected void onPrePersist() {
        if (!UserSession.isIsFirstTime()) {
            if (validate()) {
                throw new IllegalStateException(
                        String.format(
                                "Error Occured !!" + this.getError()
                        )
                );
            }
        }
    }


    public Boolean validate() {
        final ValidatorFactory factory = javax.validation.Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();
        final Set<ConstraintViolation<AppModel>> violations = validator.validate(this, Default.class, AfterLogin.class);
        this.isError = true;
        if (violations.isEmpty()) {
            this.isError = false;
            return false;
        }
        String result = violations.stream().map(
                v -> v.getMessage()).reduce("",
                (a, b) -> a + b + "\n");
        setError(result);
        return true;
    }
}
