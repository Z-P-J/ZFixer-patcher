.class public Lcom/zpj/hotfix/patch_dev/super_method/Base_Fix;
.super Ljava/lang/Object;
.source "Base.java"

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field private final synthetic mBugObj:Lcom/zpj/hotfix/patch_dev/super_method/Base;

# direct methods
.method public constructor <init>(Lcom/zpj/hotfix/patch_dev/super_method/Base;)V
    .registers 2
    .param p1, "mBugObj"

    .prologue
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Lcom/zpj/hotfix/patch_dev/super_method/Base_Fix;->mBugObj:Lcom/zpj/hotfix/patch_dev/super_method/Base;

    return-void
.end method


# virtual methods
.method public test(ILjava/lang/Integer;IDILjava/lang/Object;IIIIJIJLjava/lang/Long;)V
    .registers 23
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
        clazz = "com.zpj.hotfix.patch_dev.super_method.Base"
        method = "test"
    .end annotation

    .line 14
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "a="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move v1, p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    const-string v2, " f="

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-object v2, p7

    invoke-virtual {v0, p7}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    const-string v3, " n="

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-object/from16 v3, p17

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    const-string v4, "Base"

    invoke-static {v4, v0}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 15
    const-string v0, "hhhhhhhhhhhhhh"

    invoke-static {v0, v4}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 16
    return-void
.end method
