import { useContext } from "react";
import styles from "./styles.module.scss";
import Link from "next/link";

import { FiLogOut, FiUser } from "react-icons/fi";

import { AuthContext } from "../../contexts/AuthContext";

export function Header() {
  const { signOut } = useContext(AuthContext);
  const { user } = useContext(AuthContext);


  return (
    <header className={styles.headerContainer}>
      <div className={styles.headerContent}>
    
        <Link href="/dashboard" passHref legacyBehavior>
          <img src="/logoauto.png" width={190} height={100} />
        </Link>

        <nav className={styles.menuNav}>
          <Link href="/agendamentos" passHref legacyBehavior>
            <a>Agendamentos</a>
          </Link>
          <Link href="/servicos" passHref legacyBehavior>
            <a>Serviços</a>
          </Link>
          <Link href="/servicos-imagem" passHref legacyBehavior>
            <a>Serviços/Imagens</a>
          </Link>
          <Link href="/clientes" passHref legacyBehavior>
            <a>Clientes</a>
          </Link>
          <Link href="/veiculos" passHref legacyBehavior>
            <a>Veiculos</a>
          </Link>
          <Link href="/produtos" passHref legacyBehavior>
            <a>Produtos</a>
          </Link>
          <Link href="/finalizados" passHref legacyBehavior>
            <a>Agendamentos Concluidos</a>
          </Link>

          <button onClick={signOut}>
            <FiLogOut color="var(--red-900)" size={24} />
          </button>
        </nav>
      </div>
    </header>
  );
}
