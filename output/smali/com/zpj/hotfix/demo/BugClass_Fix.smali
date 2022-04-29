.class public Lcom/zpj/hotfix/demo/BugClass_Fix;
.super Ljava/lang/Object;
.source "BugClass.java"

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation



# direct methods
.method private static testPrivateMethod(Lcom/zpj/hotfix/demo/BugClass;)V
    .registers 3
    .param p0, "_this$p0"    # Lcom/zpj/hotfix/demo/BugClass;
    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
        method = "testPrivateMethod"
        clazz = "com.zpj.hotfix.demo.BugClass"
    .end annotation

    .line 49
    const-string v0, "TestSdk"

    const-string v1, "fix testPrivateMethod"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 50
    return-void
.end method

.method private static testStaticMethod(I)I
    .registers 3
    .param p0, "a"    # I
    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
        clazz = "com.zpj.hotfix.demo.BugClass"
        method = "testStaticMethod"
    .end annotation

    .line 54
    const-string v0, "TestSdk"

    const-string v1, "fix testStaticMethod"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 55
    add-int/lit16 v0, p0, 0x3e8

    return v0
.end method


# virtual methods
.method public static add(Lcom/zpj/hotfix/demo/BugClass;II)I
    .registers 4
    .param p0, "_this$p0"    # Lcom/zpj/hotfix/demo/BugClass;
    .param p1, "a"    # I
    .param p2, "b"    # I
    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
        method = "add"
        clazz = "com.zpj.hotfix.demo.BugClass"
    .end annotation

    .line 45
    add-int v0, p1, p2

    return v0
.end method

.method public static callBug(Lcom/zpj/hotfix/demo/BugClass;)V
    .registers 5
    .param p0, "_this$p0"    # Lcom/zpj/hotfix/demo/BugClass;
    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
        method = "callBug"
        clazz = "com.zpj.hotfix.demo.BugClass"
    .end annotation

    .line 37
    const/4 v0, 0x1

    :try_start_0
    div-int/lit8 v0, v0, 0x0

    .line 38
    .local v0, "a":I
    const-string v1, "TestSdk"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "callBug a="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 41
    nop

    .end local v0    # "a":I
    goto :goto_0

    .line 39
    :catch_0
    move-exception v0

    .line 40
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 42
    .end local v0    # "e":Ljava/lang/Exception;
    :goto_0
    return-void
.end method

.method public static getText(Lcom/zpj/hotfix/demo/BugClass;)Ljava/lang/String;
    .registers 2
    .param p0, "_this$p0"    # Lcom/zpj/hotfix/demo/BugClass;
    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
        method = "getText"
        clazz = "com.zpj.hotfix.demo.BugClass"
    .end annotation

    .line 32
    const-string v0, "fix-------------getText"

    return-object v0
.end method

.method public static test1(Lcom/zpj/hotfix/demo/BugClass;)V
    .registers 5
    .param p0, "_this$p0"    # Lcom/zpj/hotfix/demo/BugClass;
    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
        method = "test1"
        clazz = "com.zpj.hotfix.demo.BugClass"
    .end annotation

    .line 20
    invoke-static {p0}, Lcom/zpj/hotfix/demo/BugClass_Fix;->_get_test(Lcom/zpj/hotfix/demo/BugClass;)Lcom/zpj/hotfix/demo/TestClass;

    move-result-object v0


    const-string v1, "test1"

    invoke-virtual {v0, v1}, Lcom/zpj/hotfix/demo/TestClass;->test(Ljava/lang/String;)V

    .line 21
    invoke-static {p0}, Lcom/zpj/hotfix/demo/BugClass_Fix;->testPrivateMethod(Lcom/zpj/hotfix/demo/BugClass;)V
    .line 22
    const/4 v0, 0x1

    invoke-static {v0}, Lcom/zpj/hotfix/demo/BugClass_Fix;->testStaticMethod(I)I
    move-result v0

    .line 23
    .local v0, "a":I
    invoke-static {p0}, Lcom/zpj/hotfix/demo/BugClass_Fix;->_get_context(Lcom/zpj/hotfix/demo/BugClass;)Landroid/content/Context;

    move-result-object v1


    invoke-virtual {v1}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v1

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "TestSdk a="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    const/4 v3, 0x0

    invoke-static {v1, v2, v3}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v1

    invoke-virtual {v1}, Landroid/widget/Toast;->show()V

    .line 24
    return-void
.end method

.method public static test2(Lcom/zpj/hotfix/demo/BugClass;Ljava/lang/String;)V
    .registers 5
    .param p0, "_this$p0"    # Lcom/zpj/hotfix/demo/BugClass;
    .param p1, "text"    # Ljava/lang/String;
    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
        method = "test2"
        clazz = "com.zpj.hotfix.demo.BugClass"
    .end annotation

    .line 27
    invoke-static {p0}, Lcom/zpj/hotfix/demo/BugClass_Fix;->_get_test(Lcom/zpj/hotfix/demo/BugClass;)Lcom/zpj/hotfix/demo/TestClass;

    move-result-object v0


    const-string v1, "test2"

    invoke-virtual {v0, v1}, Lcom/zpj/hotfix/demo/TestClass;->test(Ljava/lang/String;)V

    .line 28
    invoke-static {p0}, Lcom/zpj/hotfix/demo/BugClass_Fix;->_get_context(Lcom/zpj/hotfix/demo/BugClass;)Landroid/content/Context;

    move-result-object v0


    invoke-virtual {v0}, Landroid/content/Context;->getApplicationContext()Landroid/content/Context;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "fix --------- "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 29
    return-void
.end method


.method private static _get_context(Lcom/zpj/hotfix/demo/BugClass;)Landroid/content/Context;
.registers 2
.param p0, "_this$p0"    # Lcom/zpj/hotfix/demo/BugClass;
.annotation system Ldalvik/annotation/Throws;
	value = {
		Ljava/lang/Exception;
	}
.end annotation

.prologue

const-string v0, "context"

invoke-static {p0, v0}, Lcom/zpj/hotfix/utils/Reflect;->getField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

move-result-object v0

check-cast v0, Landroid/content/Context;

return-object v0

.end method

.method private static _get_test(Lcom/zpj/hotfix/demo/BugClass;)Lcom/zpj/hotfix/demo/TestClass;
.registers 2
.param p0, "_this$p0"    # Lcom/zpj/hotfix/demo/BugClass;
.annotation system Ldalvik/annotation/Throws;
	value = {
		Ljava/lang/Exception;
	}
.end annotation

.prologue

const-string v0, "test"

invoke-static {p0, v0}, Lcom/zpj/hotfix/utils/Reflect;->getField(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

move-result-object v0

check-cast v0, Lcom/zpj/hotfix/demo/TestClass;

return-object v0

.end method