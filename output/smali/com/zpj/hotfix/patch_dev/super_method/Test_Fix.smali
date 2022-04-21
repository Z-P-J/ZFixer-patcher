.class public Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;
.super Lcom/zpj/hotfix/patch_dev/super_method/Base_Fix;
.source "Test.java"

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field private final synthetic mBugObj:Lcom/zpj/hotfix/patch_dev/super_method/Test;

.method public constructor <init>(Lcom/zpj/hotfix/patch_dev/super_method/Test;)V
    .registers 2
    .param p1, "test"    # Lcom/zpj/hotfix/patch_dev/super_method/Test;

    .prologue
    invoke-direct {p0, p1}, Lcom/zpj/hotfix/patch_dev/super_method/Base_Fix;-><init>(Lcom/zpj/hotfix/patch_dev/super_method/Base;)V

    iput-object p1, p0, Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;->mBugObj:Lcom/zpj/hotfix/patch_dev/super_method/Test;

    return-void
.end method

# virtual methods
.method public test()V
    .registers 3
    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
        method = "test"
        clazz = "com.zpj.hotfix.patch_dev.super_method.Test"
    .end annotation

    .line 12
    const-string v0, "Test"

    const-string v1, "test fixed!"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 13
    invoke-direct {p0}, Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;->_super_test()V
    .line 14
    return-void
.end method

.method public test(ILjava/lang/Integer;IDILjava/lang/Object;IIIIJIJLjava/lang/Long;)V
    .registers 38
    .param p1, "a"    # I
    .param p2, "b"    # Ljava/lang/Integer;
    .param p3, "c"    # I
    .param p4, "d"    # D
    .param p6, "e"    # I
    .param p7, "f"    # Ljava/lang/Object;
    .param p8, "g"    # I
    .param p9, "h"    # I
    .param p10, "i"    # I
    .param p11, "j"    # I
    .param p12, "k"    # J
    .param p14, "l"    # I
    .param p15, "m"    # J
    .param p17, "n"    # Ljava/lang/Long;
    .annotation runtime Lcom/zpj/hotfix/annotation/Fix;
        method = "test"
        clazz = "com.zpj.hotfix.patch_dev.super_method.Test"
    .end annotation

    move-object/from16 v0, p0

    iget-object v0, v0, Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;->mBugObj:Lcom/zpj/hotfix/patch_dev/super_method/Test;

    .local v0, "_thisBugObj":Lcom/zpj/hotfix/patch_dev/super_method/Test;

    move-object/from16 v1, v0

    move-object/from16 v3, p2

    move/from16 v4, p3

    move-wide/from16 v5, p4

    move/from16 v7, p6

    move/from16 v9, p8

    move/from16 v10, p9

    move/from16 v11, p10

    move/from16 v12, p11

    move-wide/from16 v13, p12

    move/from16 v15, p14

    move-wide/from16 v16, p15

    .line 18
    const-wide/16 v18, 0x64

    invoke-static/range {v18 .. v19}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v18

    const/4 v2, 0x0

    const/4 v8, 0x0

    move-object/from16 v1, p0

    invoke-super/range {v1 .. v18}, Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;->test(ILjava/lang/Integer;IDILjava/lang/Object;IIIIJIJLjava/lang/Long;)V
    .line 19
    return-void
.end method


.method private _super_test()V

.registers 5

.annotation system Ldalvik/annotation/Throws;
	value = {
		Ljava/lang/Exception;
	}
.end annotation

.prologue

const/4 v1, 0x0

new-array v0, v1, [Ljava/lang/Object;

.local v0, "arr":[Ljava/lang/Object;

iget-object v1, p0, Lcom/zpj/hotfix/patch_dev/super_method/Test_Fix;->mBugObj:Lcom/zpj/hotfix/patch_dev/super_method/Test;

const-string v2, "test"

const-string v3, "()V"

invoke-static {v1, v2, v3, v0}, Lcom/zpj/hotfix/utils/Reflect;->invokeSuperVoid(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)V

return-void.end method